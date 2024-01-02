package org.cardinal.neural.process.predictions.predictors;

import org.cardinal.data.entities.indicators.RelativeStrengthEntityDataItem;
import org.cardinal.data.services.interfaces.CoreStockService;
import org.cardinal.data.services.interfaces.NeuralPredictionService;
import org.cardinal.data.services.interfaces.ProcessParamsService;
import org.cardinal.data.services.interfaces.indicators.RelativeStrengthIndicatorService;
import org.project.model.Indicators;
import org.cardinal.neural.process.network.NetworkStore;
import org.cardinal.neural.process.predictions.Predictor;

public class RsiPredictor extends AbstractPredictor implements Predictor {

    private final RelativeStrengthIndicatorService relativeStrengthIndicatorService;
    private final NetworkStore networkStore;

    public RsiPredictor(RelativeStrengthIndicatorService relativeStrengthIndicatorService,
                        NeuralPredictionService neuralPredictionService,
                        ProcessParamsService processParamsService,
                        CoreStockService coreStockService,
                        NetworkStore networkStore) {
        super(neuralPredictionService, processParamsService,
                coreStockService);
        this.relativeStrengthIndicatorService = relativeStrengthIndicatorService;
        this.networkStore = networkStore;
    }

    public Double predict(String symbol) {
        var network = networkStore.get(Indicators.RSI, symbol);
        var rsi = relativeStrengthIndicatorService.findLast(symbol);
        var prevPriceChange = getPrevPriceChange(symbol);
        var prediction = network.predict(getGl(rsi), prevPriceChange);
        savePrediction(symbol, Indicators.RSI, rsi.getRsi(), prediction);
        return prediction;
    }

    private Double getGl(RelativeStrengthEntityDataItem rsi) {
        return rsi.getGainSumm() / rsi.getLossSumm();
    }

}
