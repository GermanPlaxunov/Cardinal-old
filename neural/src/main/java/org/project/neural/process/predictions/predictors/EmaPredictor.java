package org.project.neural.process.predictions.predictors;

import org.libra.bragi.services.interfaces.CoreStockService;
import org.libra.bragi.services.interfaces.NeuralPredictionService;
import org.libra.bragi.services.interfaces.ProcessParamsService;
import org.libra.bragi.services.interfaces.indicators.ExponentialMovingAverageService;
import org.project.model.Indicators;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.predictions.Predictor;

public class EmaPredictor extends AbstractPredictor implements Predictor {

    private final ExponentialMovingAverageService exponentialMovingAverageService;
    private final NetworkStore networkStore;

    public EmaPredictor(ExponentialMovingAverageService exponentialMovingAverageService,
                        NeuralPredictionService neuralPredictionService,
                        ProcessParamsService processParamsService,
                        CoreStockService coreStockService,
                        NetworkStore networkStore) {
        super(neuralPredictionService, processParamsService,
                coreStockService);
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
