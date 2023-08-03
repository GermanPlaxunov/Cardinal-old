package org.project.core.core.process.indicators;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.CoreStockEntity;

import java.util.List;

@RequiredArgsConstructor
public class AbsolutePriceOscillator {

    private final ExponentialMovingAverage exponentialMovingAverage;

    /**
     * Absolute price oscillator is a class of indicators that
     * builds on top of moving averages of prices to capture
     * specific short-term deviations in prices.
     *
     * @param depth             - amount of points
     * @param coreStockEntities - list of all points ordered by date
     * @return apo
     */
    public Double calculateApo(Integer depth, List<CoreStockEntity> coreStockEntities) {
        var fastTerm = depth / 2;
        var emaFast = exponentialMovingAverage.calculateEma(fastTerm, coreStockEntities);
        var emaSlow = exponentialMovingAverage.calculateEma(depth, coreStockEntities);
        return emaFast - emaSlow;
    }

}
