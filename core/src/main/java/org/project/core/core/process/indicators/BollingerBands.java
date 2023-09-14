package org.project.core.core.process.indicators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.core.core.MathUtils;
import org.project.core.core.process.indicators.model.Bband;
import org.project.data.entities.CoreStockEntity;

import java.util.List;

@Slf4j
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
     * @param coreStockEntities - list of points
     * @return bband
     */
    public Bband calculateBband(List<CoreStockEntity> coreStockEntities) {
        var symbol = coreStockEntities.get(0).getSymbol();
        log.debug("Start calculating BBAND for: {}", symbol);
        var prices = getPrices(coreStockEntities);
        var std = MathUtils.standardDeviation(prices);
        var middle = exponentialMovingAverage.calculateEma(coreStockEntities);
        var upper = middle + STDEV_FACTOR * std;
        var lower = middle - STDEV_FACTOR * std;
        log.debug("BBAND for {} UPPER: {}, MIDDLE: {}, LOWER: {}", symbol, upper, middle, lower);
        return new Bband()
                .setUpper(upper)
                .setMiddle(middle)
                .setLower(lower);
    }
}
