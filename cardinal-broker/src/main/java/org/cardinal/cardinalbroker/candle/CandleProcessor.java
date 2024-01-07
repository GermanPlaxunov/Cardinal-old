package org.cardinal.cardinalbroker.candle;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cardinal.cardinalbroker.dataprovider.candles.CandlesDataprovider;
import org.cardinal.cardinalutils.mapper.CandleMapper;
import org.cardinal.data.entities.history.CandleEntity;
import org.cardinal.data.services.interfaces.ProcessParamsService;
import org.cardinal.data.services.interfaces.history.CandleService;
import ru.tinkoff.piapi.contract.v1.CandleInterval;
import ru.tinkoff.piapi.contract.v1.HistoricCandle;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CandleProcessor {

    private final ProcessParamsService processParamsService;
    private final CandlesDataprovider candlesDataprovider;
    private final CandleService candleService;
    private final CandleMapper candleMapper;

    /**
     * В случае, если для инструмента отсутствует история цен,
     * восстанавливает историю цен, начиная с самой ранней даты.
     * Сохраняет все полученные свечи локально. В конце вызывает
     * сохранение свечей, которые лежат на отдалении от текущего
     * момента меньше, чем максимальный период
     *
     * @param figi - financial instrument global identifier
     */
    public void restoreHistory(String figi) {
        log.info("Start restoring history");
        var now = LocalDateTime.now();
        var from = processParamsService.getEarliestDateToRestoreHistory();
        var period = processParamsService.getMaxPeriodToRestoreHistorySeconds();
        var to = from.plusSeconds(period);
        //Проверка, что следующая верхняя граница не перешагнет текущий момент
        while (to.plusSeconds(period).isBefore(now)) {
            var candles = candlesDataprovider.getCandles(figi, from, to, CandleInterval.CANDLE_INTERVAL_1_MIN);
            from = from.plusSeconds(period);
            to = to.plusSeconds(period);
            candleService.saveAll(map(figi, candles));
            log.debug("Saved next {} candles for {}", candles.size(), figi);
        }
        saveTailCandles(figi);
    }

    /**
     * Получает все последние свечи по инструменту и
     * сохраняет их локально.
     *
     * @param figi - financial instrument global identifier
     */
    public void saveTailCandles(String figi) {
        log.info("Start saving tail candles");
        var maxPeriod = processParamsService.getMaxPeriodToRestoreHistorySeconds();
        var from = candleService.findLastCandleDateByFigi(figi);
        var now = LocalDateTime.now();
        if (ChronoUnit.SECONDS.between(from, now) <= maxPeriod) {
            var candles = candlesDataprovider.getCandles(figi, from, now, CandleInterval.CANDLE_INTERVAL_1_MIN);
            candleService.saveAll(map(figi, candles));
            log.debug("Saved next {} candles for {}", candles.size(), figi);
        } else {
            var to = from.plusSeconds(maxPeriod);
            //Проверка, что следующая верхняя граница не перешагнет текущий момент
            while (to.plusSeconds(maxPeriod).isBefore(now)) {
                var candles = candlesDataprovider.getCandles(figi, from, to, CandleInterval.CANDLE_INTERVAL_1_MIN);
                candleService.saveAll(map(figi, candles));
                to = to.plusSeconds(maxPeriod);
                from = from.plusSeconds(maxPeriod);
                log.debug("Saved next {} candles for {}", candles.size(), figi);
            }
            var candles = candlesDataprovider.getCandles(figi, from, now, CandleInterval.CANDLE_INTERVAL_1_MIN);
            candleService.saveAll(map(figi, candles));
            log.debug("Saved next {} candles for {}", candles.size(), figi);
        }
    }

    /**
     * Маппит historicCandle в CandleEntity
     *
     * @param figi    - financial instrument global identifier
     * @param candles - список HistoricCandle
     * @return список CandleEntity
     */
    private List<CandleEntity> map(String figi, List<HistoricCandle> candles) {
        var entities = new ArrayList<CandleEntity>();
        for (var candle : candles) {
            entities.add(candleMapper.mapToEntity(candleMapper.mapToCandle(figi, candle)));
        }
        return entities;
    }

}