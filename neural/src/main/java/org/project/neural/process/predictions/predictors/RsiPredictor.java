package org.project.neural.process.predictions.predictors;

import org.libra.bragi.entities.indicators.RelativeStrengthEntityDataItem;
import org.libra.bragi.services.interfaces.CoreStockService;
import org.libra.bragi.services.interfaces.NeuralPredictionService;
import org.libra.bragi.services.interfaces.ProcessParamsService;
import org.libra.bragi.services.interfaces.indicators.RelativeStrengthIndicatorService;
import org.project.model.Indicators;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.predictions.Predictor;

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
