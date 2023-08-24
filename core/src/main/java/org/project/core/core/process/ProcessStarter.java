package org.project.core.core.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.core.core.market.MarketDataProvider;
import org.project.core.core.process.decision.DecisionMakingCenter;
import org.project.core.core.process.indicators.IndicatorsCollector;
import org.project.core.core.process.indicators.cache.CacheDepthProvider;
import org.project.core.core.process.strategy.BasicStrategy;

@Slf4j
@RequiredArgsConstructor
public class ProcessStarter {

    private final DecisionMakingCenter decisionMakingCenter;
    private final CacheDepthProvider btcCacheDepthProvider;
    private final IndicatorsCollector indicatorsCollector;
    private final MarketDataProvider marketDataProvider;
    private final BasicStrategy basicStrategy;

    public void startProcess(String symbol) {
        var next = marketDataProvider.getNextDataPoint(symbol);
        if (checkCache(symbol)) {
            var cacheDepth = btcCacheDepthProvider.getCacheDepth(symbol);
            var processVars = indicatorsCollector.collect(symbol, cacheDepth);
            var basicStrategyResult = basicStrategy.startProcess(next);
            processVars.setBasicStrategyResult(basicStrategyResult);
            decisionMakingCenter.start(processVars);
        } else {
            log.info("Not enough cache data for {}", symbol);
        }
    }

    private boolean checkCache(String symbol) {
        return btcCacheDepthProvider.isCacheAvailable(symbol);
    }

}