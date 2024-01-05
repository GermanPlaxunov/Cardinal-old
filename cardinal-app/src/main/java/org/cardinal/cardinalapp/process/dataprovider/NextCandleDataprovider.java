package org.cardinal.cardinalapp.process.dataprovider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cardinal.cardinalbroker.dataprovider.candles.CandlesDataprovider;
import org.cardinal.data.services.interfaces.LastProvidedStockService;
import org.cardinal.data.services.interfaces.ProcessParamsService;
import ru.tinkoff.piapi.contract.v1.CandleInterval;
import ru.tinkoff.piapi.contract.v1.HistoricCandle;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class NextCandleDataprovider {

    private final LastProvidedStockService lastProvidedStockService;
    private final ProcessParamsService processParamsService;
    private final CandlesDataprovider candlesDataprovider;

    /**
     * Возвращает список свечей по инструменту, начиная
     * с даты последней известной свечи и до текущей даты.
     *
     * @param instrumentId - идентификатор инструмента
     * @return список свечей
     */
    public List<HistoricCandle> getNextCandles(String instrumentId) {
        var candles = new ArrayList<HistoricCandle>();

        if (!lastProvidedStockService.exists(instrumentId)) {
            var historyDepth = processParamsService.getInitialHistoryDepthSeconds(instrumentId);
            candles.addAll(candlesDataprovider.getCandleToCurrent(instrumentId, historyDepth, CandleInterval.CANDLE_INTERVAL_1_MIN));
        } else {
            var lastStockDate = lastProvidedStockService.find(instrumentId)
                    .getStockDate();
            var seconds = ChronoUnit.SECONDS.between(lastStockDate, LocalDateTime.now());
            candles.addAll(candlesDataprovider.getCandleToCurrent(instrumentId, seconds, CandleInterval.CANDLE_INTERVAL_1_MIN));
        }
        return candles;
    }

}
