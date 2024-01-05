package org.cardinal.cardinalbroker.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cardinal.cardinalbroker.dataprovider.candles.CandlesDataprovider;
import ru.tinkoff.piapi.contract.v1.CandleInterval;
import ru.tinkoff.piapi.contract.v1.HistoricCandle;
import ru.tinkoff.piapi.contract.v1.Share;
import ru.tinkoff.piapi.core.InvestApi;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class BrokerApi {

    private final CandlesDataprovider candlesDataprovider;
    private final InvestApi investApi;

    /**
     * Возвращает список Figi-id инструментов, открытых к торговле
     * через API.
     *
     * @return список акций
     */
    public List<String> getApiTradeAvailableSharesFigiIds() {
        return investApi.getInstrumentsService()
                .getTradableSharesSync()
                .stream()
                .filter(share -> Boolean.TRUE.equals(share.getApiTradeAvailableFlag()))
                .map(Share::getFigi)
                .toList();
    }

    /**
     * Предоставляет исторические данные по инструменту.
     *
     * @param figi         - id инструмента
     * @param depthSeconds - глубина поиска
     * @param interval     - инстервал между свечами
     * @return список свеч
     */
    public List<HistoricCandle> getPriceHistoryToCurrent(String figi, Long depthSeconds, CandleInterval interval) {
        return candlesDataprovider.getCandleToCurrent(figi, depthSeconds, interval);
    }

}
