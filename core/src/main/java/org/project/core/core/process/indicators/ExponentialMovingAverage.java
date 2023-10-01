package org.project.core.core.process.indicators;

import lombok.extern.slf4j.Slf4j;
import org.project.data.entities.CoreStockEntity;

import java.util.List;

@Slf4j
public class ExponentialMovingAverage extends AbstractIndicator {

    /**
     * The EMA is similar to the simple moving average, but, instead of weighing all prices in the
     * history equally, it places more weight on the most recent price observation and less weight
     * on the older price observations.
     *
     * @param coreStockEntities - list of all points ordered by date
     * @return ema
     */
    public Double calculateEma(List<CoreStockEntity> coreStockEntities, Long cacheDepth) {
        var symbol = coreStockEntities.get(0).getSymbol();
        log.debug("Start calculating EMA for {}", symbol);
        var stocks = getCachedStocks(coreStockEntities, cacheDepth);
        var depth = stocks.size();
        var ema = stocks.get(0).getClose();
        var ny = getNy(depth);
        for (var i = 1; i < depth; i++) {
            ema = getEma(stocks.get(i).getClose(), ema, ny);
        }
        log.debug("EMA for {} is {}", symbol, ema);
        return ema;
    }

    private Double getEma(Double currentPrice, Double oldEma, Double ny) {
        return (currentPrice - oldEma) * ny + oldEma;
    }

    private Double getNy(Integer N) {
        return 2.0 / (N + 1);
    }
}
