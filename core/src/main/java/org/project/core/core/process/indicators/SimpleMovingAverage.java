package org.project.core.core.process.indicators;

import org.project.data.entities.CoreStockEntity;

import java.util.List;

public class SimpleMovingAverage extends AbstractIndicator {

    /**
     * Simple average price over a period of time.
     *
     * @param depth             - amount of points
     * @param coreStockEntities - list of all points ordered by date
     * @return sma
     */
    public Double calculateSma(Integer depth, List<CoreStockEntity> coreStockEntities) {
        if (coreStockEntities.size() >= 20) {
            if (depth == 0 || depth < 0) {
                depth = 300;
            }
            var prices = getPrices(coreStockEntities, depth);
            var summ = prices.stream()
                    .reduce(0.0, Double::sum);
            return summ / depth;
        }
        return null;
    }

}
