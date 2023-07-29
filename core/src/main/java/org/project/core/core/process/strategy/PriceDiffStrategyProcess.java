package org.project.core.core.process.strategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.core.core.process.deal.DealMaker;
import org.project.core.core.process.strategy.diff.DiffSignalCalculator;
import org.project.data.services.interfaces.PriceDiffSignalService;
import org.project.model.DiffSignal;
import org.project.model.MarketStock;

@Slf4j
@RequiredArgsConstructor
public class PriceDiffStrategyProcess {
    private final PriceDiffSignalService priceDiffSignalService;
    private final DiffSignalCalculator diffSignalCalculator;
    private final Double positionAmount;
    private final DealMaker dealMaker;

    public void startProcess(MarketStock marketStock) {
        var count = priceDiffSignalService.count(marketStock.getSymbol());
        if (count == 0) {
            diffSignalCalculator.createInitialSignal(marketStock);
        } else {
            var signal = diffSignalCalculator.createDiffSignal(marketStock);
            makeDealAccordingSignal(signal, marketStock, positionAmount);
        }
    }

    private void makeDealAccordingSignal(DiffSignal signal, MarketStock stock, Double amount) {
        log.info("Price diff: {}, signal: {}", signal.getDiff(), signal.getPositionSignal());
        if (signal.getPositionSignal() == 1) {
            dealMaker.openLongPosition(stock.getSymbol(), amount);
        }
        if (signal.getPositionSignal() == -1) {
            dealMaker.closeLongPosition(stock.getSymbol());
        }
    }
}
