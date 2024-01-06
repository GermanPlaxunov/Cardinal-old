package org.cardinal.cardinalbroker.candle;

import lombok.RequiredArgsConstructor;
import org.cardinal.cardinalbroker.dataprovider.candles.CandlesDataprovider;
import org.cardinal.cardinalutils.mapper.CandleMapper;
import org.cardinal.data.entities.history.CandleEntity;
import org.cardinal.data.services.interfaces.ProcessParamsService;
import org.cardinal.data.services.interfaces.history.CandleService;
import ru.tinkoff.piapi.contract.v1.CandleInterval;
import ru.tinkoff.piapi.contract.v1.HistoricCandle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CandleProcessor {

    private final ProcessParamsService processParamsService;
    private final CandlesDataprovider candlesDataprovider;
    private final CandleService candleService;
    private final CandleMapper candleMapper;

    /**
     * В случае, если для инструмента отсутствует история цен,
     * восстанавливает историю цен, начиная с самой ранней даты.
     * Сохраняет все полученные свечи локально.
     *
     * @param figi - financial instrument global identifier
     */
    public void restoreHistory(String figi) {
        var now = LocalDateTime.now();
        var from = processParamsService.getEarliestDateToRestoreHistory();
        var period = processParamsService.getMaxPeriodToRestoreHistorySeconds();
        var to = from.plusSeconds(period);
        while (to.isBefore(now)) {
            var candles = candlesDataprovider.getCandles(figi, from, to, CandleInterval.CANDLE_INTERVAL_1_MIN);
            from = from.plusSeconds(period);
            to = to.plusSeconds(period);
            candleService.saveAll(map(candles));
        }
    }

    /**
     * Получает все последние свечи по инструменту и
     * сохраняет их в локально.
     *
     * @param figi - financial instrument global identifier
     */
    public void saveLastCandles(String figi) {
        var lastDate = candleService.findLastCandleDateByFigi(figi);
        var now = LocalDateTime.now();
        var candles = candlesDataprovider.getCandles(figi, lastDate, now, CandleInterval.CANDLE_INTERVAL_1_MIN);
        candleService.saveAll(map(candles));
    }

    /**
     * Маппит historicCandle в CandleEntity
     *
     * @param candles - список HistoricCandle
     * @return список CandleEntity
     */
    private List<CandleEntity> map(List<HistoricCandle> candles) {
        var entities = new ArrayList<CandleEntity>();
        for (var candle : candles) {
            entities.add(candleMapper.mapToEntity(candleMapper.mapToCandle(candle)));
        }
        return entities;
    }

}