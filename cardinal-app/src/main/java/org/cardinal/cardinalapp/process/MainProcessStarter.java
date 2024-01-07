package org.cardinal.cardinalapp.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cardinal.cardinalbroker.candle.CandleProcessor;
import org.cardinal.data.services.interfaces.ProcessParamsService;
import org.cardinal.data.services.interfaces.ShareService;
import org.cardinal.data.services.interfaces.history.CandleService;
import org.cardinal.model.job.ProcessStarter;

@Slf4j
@RequiredArgsConstructor
public class MainProcessStarter implements ProcessStarter {

    private final ProcessParamsService processParamsService;
    private final CandleProcessor candleProcessor;
    private final CandleService candleService;
    private final ShareService shareService;

    @Override
    public void startProcess() {
        log.info("Started main process");
        var figi = getFigiOfActiveInstrument();
        updateData(figi);
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
