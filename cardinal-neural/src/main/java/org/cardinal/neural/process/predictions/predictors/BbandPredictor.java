package org.cardinal.neural.process.predictions.predictors;

import org.cardinal.data.services.interfaces.CoreStockService;
import org.cardinal.data.services.interfaces.NeuralPredictionService;
import org.cardinal.data.services.interfaces.ProcessParamsService;
import org.cardinal.data.services.interfaces.indicators.BollingerBandsService;
import org.cardinal.model.Indicators;
import org.cardinal.neural.process.network.NetworkStore;
import org.cardinal.neural.process.predictions.Predictor;

public class BbandPredictor extends AbstractPredictor implements Predictor {

    private final BollingerBandsService bollingerBandsService;
    private final NetworkStore networkStore;

    public BbandPredictor(NeuralPredictionService neuralPredictionService,
                          BollingerBandsService bollingerBandsService,
                          ProcessParamsService processParamsService,
                          CoreStockService coreStockService,
                          NetworkStore networkStore) {
        super(neuralPredictionService, processParamsService,
                coreStockService);
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
