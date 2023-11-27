package org.project.core.core.process.indicators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.core.core.MathUtils;
import org.project.model.indicators.Bband;
import org.project.data.entities.CoreStockEntity;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class BollingerBands extends AbstractIndicator {
    private final Double STDEV_FACTOR = 2.0; //TODO: To params
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
    public Bband calculateBband(List<CoreStockEntity> coreStockEntities, Long cacheDepth) {
        var symbol = coreStockEntities.get(0).getSymbol();
        var stocks = getCachedStocks(coreStockEntities, cacheDepth);
        log.debug("Start calculating BBAND for: {}", symbol);
        var std = MathUtils.standardDeviation(stocks.stream()
                .map(CoreStockEntity::getClose)
                .toList());
        var middle = exponentialMovingAverage.calculateEma(coreStockEntities, cacheDepth);
        var upper = middle + STDEV_FACTOR * std;
        var lower = middle - STDEV_FACTOR * std;
        log.debug("BBAND for {} UPPER: {}, MIDDLE: {}, LOWER: {}", symbol, upper, middle, lower);
        return new Bband()
                .setUpper(upper)
                .setMiddle(middle)
                .setLower(lower);
    }
}
