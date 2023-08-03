package org.project.core.core.process.indicators;

import org.project.data.entities.CoreStockEntity;

import java.util.List;

public class ExponentialMovingAverage extends AbstractIndicator {

    /**
     * The EMA is similar to the simple moving average, but, instead of weighing all prices in the
     * history equally, it places more weight on the most recent price observation and less weight
     * on the older price observations.
     *
     * @param depth             - amount of points
     * @param coreStockEntities - list of all points ordered by date
     * @return ema
     */
    public Double calculateEma(Integer depth, List<CoreStockEntity> coreStockEntities) {
        if (coreStockEntities.size() > 20) {
            if (depth == 0 || depth < 0) {
                var prices = getPrices(coreStockEntities, depth);
                Double ema = prices.get(0);
                for (var i = 1; i < depth; i++) {
                    ema = getEma(prices.get(i), ema, depth);
                }
                return ema;
            }
        }
        return null;
    }

    private Double getEma(Double currentPrice, Double oldEma, Integer N) {
        var ny = getNy(N);
        return (currentPrice - oldEma) * ny + oldEma;
    }

    private Double getNy(Integer N) {
        return 2.0 / (N + 1);
    }
}
