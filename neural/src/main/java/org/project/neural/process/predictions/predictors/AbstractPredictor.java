package org.project.neural.process.predictions.predictors;

import lombok.RequiredArgsConstructor;
import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.ProcessParamsService;
import org.project.model.Indicators;

@RequiredArgsConstructor
public class AbstractPredictor {

    private final ProcessParamsService processParamsService;
    private final CoreStockService coreStockService;

    /**
     * Find the very last stock received from
     * market and the previous one and calculates
     * the difference of the close prices between them.
     *
     * @param symbol - the name of the stock.
     * @return the price change.
     */
    protected Double getPrevPriceChange(String symbol) {
        var stepBack = processParamsService.getStepBackSeconds(symbol, Indicators.APO);
        var lastStock = coreStockService.findLast(symbol);
        var prevStock = coreStockService.findPrevious(symbol, lastStock.getDate(), stepBack);
        return prevStock.getClose() - lastStock.getClose();
    }

}
