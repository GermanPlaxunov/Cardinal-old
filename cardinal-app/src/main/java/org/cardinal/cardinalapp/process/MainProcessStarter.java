package org.cardinal.cardinalapp.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cardinal.cardinalbroker.candle.CandleProcessor;
import org.cardinal.core.strategy.MainTradingStrategy;
import org.cardinal.data.services.interfaces.PositionService;
import org.cardinal.data.services.interfaces.ProcessParamsService;
import org.cardinal.data.services.interfaces.ShareService;
import org.cardinal.data.services.interfaces.history.CandleService;
import org.cardinal.model.job.ProcessStarter;

@Slf4j
@RequiredArgsConstructor
public class MainProcessStarter implements ProcessStarter {

    private final ProcessParamsService processParamsService;
    private final MainTradingStrategy mainTradingStrategy;
    private final PositionService positionService;
    private final CandleProcessor candleProcessor;
    private final CandleService candleService;
    private final ShareService shareService;

    @Override
    public void startProcess() {
        log.info("Started main process");
        var figi = getFigiOfActiveInstrument();
        updateData(figi);
        positionExistenceFork(figi);
    }

    /**
     * Процесс может идти по двум основным веткам:
     * обработка существующей позиции или проверка
     * возможности открытия новой позиции.
     *
     * @param figi - financial instrument global identifier
     */
    private void positionExistenceFork(String figi) {
        if (positionService.ifOpenPosition(figi)) {
            mainTradingStrategy.closeExistingPositionProcess(figi);
        } else {
            mainTradingStrategy.openNewPositionProcess(figi);
        }
    }

    /**
     * Запускает процесс обработки существующей позиции.
     *
     * @param figi - financial instrument global identifier
     */
    private void processCurrentPosition(String figi) {

    }

    /**
     * Запускает процесс проверки риска при открытии новой
     * позиции.
     *
     * @param figi - financial instrument global identifier
     */
    private void processNewPosition(String figi) {

    }

    /**
     * Обновляет данные по инструменту. Если данных нет, полностью
     * восстанавливает историю, иначе тянет последние свечи.
     *
     * @param figi - financial instrument global identifier
     */
    private void updateData(String figi) {
        if (candleService.existHistory(figi)) {
            candleProcessor.saveTailCandles(figi);
        } else {
            candleProcessor.restoreHistory(figi);
        }
    }

    /**
     * Возвращает financial instrument global identifier
     * текущего торгуемого инструмента.
     *
     * @return figi
     */
    private String getFigiOfActiveInstrument() {
        var name = processParamsService.getActiveTradeInstrumentName();
        return shareService.findByName(name)
                .getFigi();
    }

}
