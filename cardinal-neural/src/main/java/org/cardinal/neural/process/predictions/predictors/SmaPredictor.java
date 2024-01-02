package org.cardinal.neural.process.predictions.predictors;

import org.cardinal.data.services.interfaces.CoreStockService;
import org.cardinal.data.services.interfaces.NeuralPredictionService;
import org.cardinal.data.services.interfaces.ProcessParamsService;
import org.cardinal.data.services.interfaces.indicators.SimpleMovingAverageService;
import org.project.model.Indicators;
import org.cardinal.neural.process.network.NetworkStore;
import org.cardinal.neural.process.predictions.Predictor;

public class SmaPredictor extends AbstractPredictor implements Predictor {

    private final SimpleMovingAverageService simpleMovingAverageService;
    private final NetworkStore networkStore;

    public SmaPredictor(SimpleMovingAverageService simpleMovingAverageService,
                        NeuralPredictionService neuralPredictionService,
                        ProcessParamsService processParamsService,
                        CoreStockService coreStockService,
                        NetworkStore networkStore) {
        super(neuralPredictionService, processParamsService,
                coreStockService);
        this.simpleMovingAverageService = simpleMovingAverageService;
        this.networkStore = networkStore;
    }

    @Override
    public Double predict(String symbol) {
        var network = networkStore.get(Indicators.SMA, symbol);
        var sma = simpleMovingAverageService.findLast(symbol);
        var prevPriceChange = getPrevPriceChange(symbol);
        var prediction = network.predict(sma.getValue(), prevPriceChange);
        savePrediction(symbol, Indicators.SMA, sma.getValue(), prediction);
        return prediction;
    }
}
