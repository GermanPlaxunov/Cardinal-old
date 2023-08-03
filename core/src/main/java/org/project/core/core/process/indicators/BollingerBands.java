package org.project.core.core.process.indicators;

import lombok.RequiredArgsConstructor;
import org.project.core.core.MathUtils;
import org.project.core.core.process.indicators.model.Bband;
import org.project.data.entities.CoreStockEntity;

import java.util.List;

@RequiredArgsConstructor
public class BollingerBands extends AbstractIndicator {
    private final Double STDEV_FACTOR = 2.0;
    private final ExponentialMovingAverage exponentialMovingAverage;

    /**
     * This band represents the expected volatility of the prices by
     * treating the moving average of the price as the reference price. When price move
     * outside of these bands, that can be interpreted as a breakout/trend signal or an
     * overbought/sold mean reversion signal.
     *
     * @param depth             - amount of points
     * @param coreStockEntities - list of all points
     * @return bband
     */
    public Bband calculateBband(Integer depth, List<CoreStockEntity> coreStockEntities) {
        var prices = getPrices(coreStockEntities, depth);
        var std = MathUtils.standardDeviation(prices);
        var middle = exponentialMovingAverage.calculateEma(depth, coreStockEntities);
        var upper = middle + STDEV_FACTOR * std;
        var lower = middle - STDEV_FACTOR * std;
        return new Bband()
                .setUpper(upper)
                .setMiddle(middle)
                .setLower(lower);
    }
}
