package org.cardinal.core.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cardinal.core.deal.DealMaker;
import org.cardinal.core.process.market.MarketDataProvider;
import org.cardinal.core.process.strategy.MainStrategy;
import org.cardinal.data.services.interfaces.CoreStockService;
import org.cardinal.model.CoreStock;
import org.cardinal.model.ProcessVars;
import org.cardinal.model.job.ProcessStarter;

@Slf4j
@RequiredArgsConstructor
public class CoreProcessStarter implements ProcessStarter {

    private final MarketDataProvider marketDataProvider;
    private final CoreStockService coreStockService;
    private final MainStrategy mainStrategy;
    private final DealMaker dealMaker;

    /**
     * Entry point of trading process.
     * Obtains next stock entry and launch the process.
     *
     * @param symbol - the name of the stock
     */
    @Override
    public void startProcess(String symbol) {
        if (marketDataProvider.hasNextStock(symbol)) {
            var nextStock = marketDataProvider.getNextDataPoint(symbol);
            if (coreStockService.checkCacheExists(symbol)) {
                var processVars = initProcessVars(symbol, nextStock);
                launchProcess(processVars);
            } else {
                log.info("Not enough cache to process data");
            }
        } else {
            log.info("Next entry of the {} is not available yet.", symbol);
        }
    }

    /**
     * Launches main strategy and Core Decision System (CDS).
     * After all analyze is done act to market.
     *
     * @param processVars - process data
     */
    private void launchProcess(ProcessVars<CoreStock> processVars) {
        var symbol = processVars.getSymbol();
        mainStrategy.launchStrategy(processVars);
        var strategyResult = processVars.getMainStrategyResult();
        if (strategyResult.isShouldNewPositionBeOpen()) {
            var amountCurrency = processVars.getAmountCurr();
            dealMaker.openLongPosition(symbol, amountCurrency);
        }
        if (strategyResult.isShouldCurrentPositionBeClosed()) {
            dealMaker.closeLongPosition(symbol);
        }
    }

    private ProcessVars<CoreStock> initProcessVars(String symbol, CoreStock lastStock) {
        return new ProcessVars<CoreStock>()
                .setSymbol(symbol)
                .setLastProvidedStock(lastStock);
    }

}
