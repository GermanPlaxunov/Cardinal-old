package org.project.core.core.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.core.core.market.MarketDataProvider;
import org.project.core.core.process.decision.DecisionMakingCenter;
import org.project.core.core.process.indicators.IndicatorsCollector;
import org.project.data.services.interfaces.CoreStockService;

@Slf4j
@RequiredArgsConstructor
public class ProcessStarter {

    private final DecisionMakingCenter decisionMakingCenter;
    private final IndicatorsCollector indicatorsCollector;
    private final MarketDataProvider marketDataProvider;
    private final CoreStockService coreStockService;

    public void startProcess(String symbol) {
        var next = marketDataProvider.getNextDataPoint(symbol);
        if (coreStockService.checkCacheExists(symbol)) {
            var processVars = indicatorsCollector.collect(symbol);
            decisionMakingCenter.start(processVars);
        } else {
            log.info("Not enough cache data for {}", symbol);
        }
    }

}
