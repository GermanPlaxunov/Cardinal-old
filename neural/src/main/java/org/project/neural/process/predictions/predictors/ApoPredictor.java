package org.project.neural.process.predictions.predictors;

import lombok.RequiredArgsConstructor;
import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.ProcessParamsService;
import org.project.data.services.interfaces.indicators.AbsolutePriceOscillatorService;
import org.project.model.Indicators;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.predictions.Predictor;

@RequiredArgsConstructor
public class ApoPredictor implements Predictor {

    private final AbsolutePriceOscillatorService absolutePriceOscillatorService;
    private final ProcessParamsService processParamsService;
    private final CoreStockService coreStockService;
    private final NetworkStore networkStore;

    /**
     * Makes prediction using the last APO
     * value and the last price delta.
     *
     * @param symbol - the name of the stock.
     * @return price change prediction.
     */
    @Override
    public Double predict(String symbol) {
        var network = networkStore.get(Indicators.APO, symbol);
        var apo = absolutePriceOscillatorService.findLast(symbol);
        var prevPriceChange = getPrevPriceChange(symbol);
        var result = network.predict(apo.getValue(), prevPriceChange);
        return result;
    }


    /**
     * Find the very last stock received from
     * market and the previous one and calculates
     * the difference of the close prices between them.
     *
     * @param symbol - the name of the stock.
     * @return the price change.
     */
    private Double getPrevPriceChange(String symbol) {
        var stepBack = processParamsService.getStepBackSeconds(symbol, Indicators.APO);
        var lastStock = coreStockService.findLast(symbol);
        var prevStock = coreStockService.findPrevious(symbol, lastStock.getDate(), stepBack);
        return prevStock.getClose() - lastStock.getClose();
    }

}
