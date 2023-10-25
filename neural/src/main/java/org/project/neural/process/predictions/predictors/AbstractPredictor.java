package org.project.neural.process.predictions.predictors;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.CoreStockEntity;
import org.project.data.entities.NeuralPredictionEntity;
import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.NeuralPredictionService;
import org.project.data.services.interfaces.ProcessParamsService;
import org.project.model.Indicators;

@RequiredArgsConstructor
public class AbstractPredictor {

    private final NeuralPredictionService neuralPredictionService;
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

    /**
     * Save predicted price change in Database.
     *
     * @param symbol                - the name of the stock.
     * @param indicator             - the name of the indicator.
     * @param indicatorValue        - the value of the indicator.
     * @param priceChangePrediction - predicted value.
     */
    protected void savePrediction(String symbol, Indicators indicator, Double indicatorValue,
                                  Double priceChangePrediction) {
        var lastStock = coreStockService.findLast(symbol);
        neuralPredictionService.save(new NeuralPredictionEntity()
                .setSymbol(symbol)
                .setIndicatorName(indicator.name())
                .setIndicatorValue(indicatorValue)
                .setCurrentPrice(lastStock.getClose())
                .setPrevToCurrPriceDelta(getPriceDelta(lastStock))
                .setPriceChangePrediction(priceChangePrediction)
                .setDate(lastStock.getDate()));
    }

    /**
     * Returns the difference between
     * current price and previous price.
     *
     * @param lastStock - the very last stock.
     * @return price difference.
     */
    private Double getPriceDelta(CoreStockEntity lastStock) {
        var depth = 30L; //TODO: in params
        var previous = coreStockService.findPrevious(lastStock.getSymbol(), lastStock.getDate(), depth);
        return lastStock.getClose() - previous.getClose();
    }

}
