package org.project.core.core.process.indicators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.data.entities.CoreStockEntity;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class AbsolutePriceOscillator extends AbstractIndicator {

    private final ExponentialMovingAverage exponentialMovingAverage;

    /**
     * Absolute price oscillator is a class of indicators that
     * builds on top of moving averages of prices to capture
     * specific short-term deviations in prices.
     *
     * @param coreStockEntities - list of points ordered by date
     * @return apo
     */
    public Double calculateApo(List<CoreStockEntity> coreStockEntities, Long cacheDepth) {
        var symbol = coreStockEntities.get(0).getSymbol();
        log.debug("Start calculating APO for {}", symbol);
        var shortTermCacheDepth = cacheDepth / 2;
        var emaFast = exponentialMovingAverage.calculateEma(coreStockEntities, shortTermCacheDepth);
        var emaSlow = exponentialMovingAverage.calculateEma(coreStockEntities, cacheDepth);
        log.debug("EMA_FAST: {}, EMA_SLOW: {}", emaFast, emaSlow);
        return emaFast - emaSlow;
    }

}
