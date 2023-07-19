package org.project.core.core.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.core.core.market.MarketDataProvider;
import org.project.core.core.process.strategy.PriceDiffStrategyProcess;

@Slf4j
@RequiredArgsConstructor
public class ProcessStarter {

    private final PriceDiffStrategyProcess diffStrategyProcess;
    private final MarketDataProvider marketDataProvider;

    public void startProcess(String stock) {
        var next = marketDataProvider.getNextDataPoint(stock);
        diffStrategyProcess.startProcess(next);
    }

}
