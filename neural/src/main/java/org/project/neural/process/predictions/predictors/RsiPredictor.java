package org.project.neural.process.predictions.predictors;

import lombok.RequiredArgsConstructor;
import org.project.data.services.interfaces.indicators.RelativeStrengthIndicatorService;
import org.project.model.Indicators;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.predictions.Predictor;

@RequiredArgsConstructor
public class RsiPredictor implements Predictor {

    private final RelativeStrengthIndicatorService relativeStrengthIndicatorService;
    private final NetworkStore networkStore;

    public Double predict(String symbol) {
        var network = networkStore.get(Indicators.RSI, symbol);
        var rsi = relativeStrengthIndicatorService.findLast(symbol);
        //Возможно надо будет заменить на отношение Gain к Loss
        var result = network.predict(rsi.getGainSumm(), rsi.getLossSumm());
        return result;
    }

}
