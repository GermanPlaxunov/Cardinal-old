package org.project.neural.process.predictions.predictors;

import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.LastProvidedStockService;
import org.project.data.services.interfaces.NeuralPredictionService;
import org.project.data.services.interfaces.ProcessParamsService;
import org.project.data.services.interfaces.indicators.ExponentialMovingAverageService;
import org.project.model.Indicators;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.predictions.Predictor;

public class EmaPredictor extends AbstractPredictor implements Predictor {

    private final ExponentialMovingAverageService exponentialMovingAverageService;
    private final NetworkStore networkStore;

    public EmaPredictor(ExponentialMovingAverageService exponentialMovingAverageService,
                        LastProvidedStockService lastProvidedStockService,
                        NeuralPredictionService neuralPredictionService,
                        ProcessParamsService processParamsService,
                        CoreStockService coreStockService,
                        NetworkStore networkStore) {
        super(lastProvidedStockService, neuralPredictionService,
                processParamsService, coreStockService);
        this.exponentialMovingAverageService = exponentialMovingAverageService;
        this.networkStore = networkStore;
    }

    @Override
    public Double predict(String symbol) {
        var network = networkStore.get(Indicators.EMA, symbol);
        var ema = exponentialMovingAverageService.findLast(symbol);
        var prevPriceChange = getPrevPriceChange(symbol);
        var prediction = network.predict(ema.getValue(), prevPriceChange);
        savePrediction(symbol, Indicators.EMA, ema.getValue(), prediction);
        return prediction;
    }
}
