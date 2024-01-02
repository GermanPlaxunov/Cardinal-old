package org.project.core.core.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cardinal.data.services.interfaces.CoreStockService;
import org.cardinal.data.services.interfaces.PositionService;
import org.cardinal.data.services.interfaces.ProcessParamsService;
import org.project.core.core.market.MarketDataProvider;
import org.project.core.core.process.broker.commission.CommissionProcessor;
import org.project.core.core.process.data.trend.TrendProvider;
import org.project.core.core.process.deal.DealMaker;
import org.project.core.core.process.indicators.IndicatorsCollector;
import org.project.core.core.process.indicators.IndicatorsPredictionsCollector;
import org.project.core.core.process.strategy.MainStrategy;
import org.project.core.mapper.CoreStockMapper;
import org.project.model.CoreStock;
import org.project.model.ProcessVars;
import org.project.model.job.ProcessStarter;

@Slf4j
@RequiredArgsConstructor
public class TradeProcessStarter implements ProcessStarter {

    private final IndicatorsPredictionsCollector indicatorsPredictionsCollector;
    private final ProcessParamsService processParamsService;
    private final CommissionProcessor commissionProcessor;
    private final IndicatorsCollector indicatorsCollector;
    private final MarketDataProvider marketDataProvider;
    private final CoreStockService coreStockService;
    private final CoreStockMapper coreStockMapper;
    private final PositionService positionService;
    private final TrendProvider trendProvider;
    private final MainStrategy mainStrategy;
    private final DealMaker dealMaker;

    /**
     * Entry point of process.
     * Collect all process data and launch
     * the strategy.
     *
     * @param symbol - the name of the stock.
     */
    @Override
    public void startProcess(String symbol) {
        var next = marketDataProvider.getNextDataPoint(symbol);
        if (coreStockService.checkCacheExists(symbol)) {
            var processVars = initProcessVars(symbol, next.getClose());
            var cacheDepth = processParamsService.getMaximumCacheDepth(symbol);
            var coreStocks = coreStockService.findCache(symbol, cacheDepth);
            var stocks = coreStockMapper.mapAllToCore(coreStocks);
            processVars.setStocks(stocks);
            setOpenPositionFlag(processVars);
            commissionProcessor.calculateCommission(processVars);
            indicatorsCollector.collect(symbol, processVars);
            indicatorsPredictionsCollector.collectPredictions(processVars);
            processVars.setTrendData(trendProvider.getTrend(symbol, stocks));
            launchStrategy(processVars);
        } else {
            log.info("Not enough cache data for {}", symbol);
        }
    }

    /**
     * Launch Root strategy of decision.
     * Should be called after all process
     * data collected in the processVars.
     *
     * @param processVars - all process data.
     */
    private void launchStrategy(ProcessVars processVars) {
        var symbol = processVars.getSymbol();
        if (processVars.getIsAnyOpenPosition()) {
            var result = mainStrategy.ifCurrentPositionShouldBeClosed(processVars);
            if (result.isShouldCurrentPositionBeClosed()) {
                dealMaker.closeLongPosition(symbol);
            }
        } else {
            var result = mainStrategy.ifNewPositionShouldBeOpened(processVars);
            if (result.isShouldNewPositionBeOpen()) {
                dealMaker.openLongPosition(symbol, result.getAmount());
            }
        }
    }

    /**
     * Checks if there is any open position for this symbol.
     *
     * @param processVars - process data
     */
    private void setOpenPositionFlag(ProcessVars<CoreStock> processVars) {
        var symbol = processVars.getSymbol();
        var isAnyOpenPosition = positionService.ifOpenPosition(symbol);
        processVars.setIsAnyOpenPosition(isAnyOpenPosition);
    }

    /**
     * Creates processVars with initial data.
     *
     * @param symbol       - name of the stock
     * @param currentPrice - current stock price
     * @return processVars
     */
    private ProcessVars<CoreStock> initProcessVars(String symbol, Double currentPrice) {
        return new ProcessVars<CoreStock>()
                .setSymbol(symbol)
                .setCurrentPrice(currentPrice)
                .setAmountCurr(1.0);
    }

}
