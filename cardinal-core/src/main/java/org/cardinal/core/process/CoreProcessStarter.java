package org.cardinal.core.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cardinal.core.strategy.MainStrategy;
import org.cardinal.data.services.interfaces.CoreStockService;
import org.cardinal.core.market.MarketDataProvider;
import org.project.model.CoreStock;
import org.project.model.ProcessVars;
import org.project.model.job.ProcessStarter;

@Slf4j
@RequiredArgsConstructor
public class CoreProcessStarter implements ProcessStarter {

    private final MarketDataProvider marketDataProvider;
    private final CoreStockService coreStockService;
    private final MainStrategy mainStrategy;

    /**
     * Entry point of trading process.
     * Obtains next stock entry and launch the process.
     *
     * @param symbol - the name of the stock
     */
    @Override
    public void startProcess(String symbol) {
        if (marketDataProvider.hasNextStock(symbol)) {
            marketDataProvider.saveNextDataPoint(symbol);
            if (coreStockService.checkCacheExists(symbol)) {
                launchProcess(symbol);
            } else {
                log.info("Not enough cache to process data");
            }
        } else {
            log.info("Next entry of the {} is not available yet.", symbol);
        }
    }

    private void launchProcess(String symbol) {
        var processVars = initProcessVars(symbol);
        mainStrategy.launchStrategy(processVars);
    }

    private ProcessVars<CoreStock> initProcessVars(String symbol) {
        return new ProcessVars<CoreStock>()
                .setSymbol(symbol);
    }

}
