package org.project.neural.process.predictions.predictors;

import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.LastProvidedStockService;
import org.project.data.services.interfaces.NeuralPredictionService;
import org.project.data.services.interfaces.ProcessParamsService;
import org.project.data.services.interfaces.indicators.BollingerBandsService;
import org.project.model.Indicators;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.predictions.Predictor;

public class BbandPredictor extends AbstractPredictor implements Predictor {

    private final BollingerBandsService bollingerBandsService;
    private final NetworkStore networkStore;

    public BbandPredictor(LastProvidedStockService lastProvidedStockService,
                          NeuralPredictionService neuralPredictionService,
                          BollingerBandsService bollingerBandsService,
                          ProcessParamsService processParamsService,
                          CoreStockService coreStockService,
                          NetworkStore networkStore) {
        super(lastProvidedStockService, neuralPredictionService,
                processParamsService, coreStockService);
        this.bollingerBandsService = bollingerBandsService;
        this.networkStore = networkStore;
    }

    @Override
    public Double predict(String symbol) {
        var network = networkStore.get(Indicators.BBAND, symbol);
        var bband = bollingerBandsService.findLast(symbol);
        var prevPriceChange = getPrevPriceChange(symbol);
        //TODO: Create network with 3+ inputs
        var prediction = network.predict(bband.getUpper(), bband.getLower());
        savePrediction(symbol, Indicators.BBAND, bband.getMiddle(), prediction);//TODO: How to save BBAND
        return prediction;
    }


}
