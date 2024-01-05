package org.cardinal.cardinalbroker.dataprovider.candles;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.tinkoff.piapi.contract.v1.CandleInterval;
import ru.tinkoff.piapi.contract.v1.HistoricCandle;
import ru.tinkoff.piapi.core.InvestApi;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CandlesDataprovider {

    private final InvestApi investApi;

    /**
     * Получение исторических данных в синхронном режиме.
     *
     * @param figiId       - id инструмента
     * @param depthSeconds - глубина поиска
     * @param interval     - интервал между свечами
     * @return список свечей
     */
    public List<HistoricCandle> getCandleToCurrent(String figiId,
                                                    Long depthSeconds,
                                                    CandleInterval interval) {
        var from = Instant.now().minus(depthSeconds, ChronoUnit.SECONDS);
        var to = Instant.now();
        return getCandlesSync(figiId, from, to, interval);
    }

    /**
     * Метод обращения к Tinkoff API. В синхронном режиме
     * получает список исторических свеч.
     *
     * @param instrumentId - id инструмента
     * @param from         - дата с
     * @param to           - дата до
     * @param interval     - величина интервала одной свечи
     * @return исторические данные цены
     */
    private List<HistoricCandle> getCandlesSync(String instrumentId,
                                                Instant from,
                                                Instant to,
                                                CandleInterval interval) {
        return investApi.getMarketDataService()
                .getCandlesSync(instrumentId, from, to, interval);
    }

}
