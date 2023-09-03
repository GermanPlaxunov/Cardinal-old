package org.project.neural.process.predictions;

import lombok.RequiredArgsConstructor;
import org.project.data.services.interfaces.indicators.RelativeStrengthIndicatorService;
import org.project.neural.process.network.NetworkStore;

@RequiredArgsConstructor
public class RsiPredictor implements Predictor {

    private final RelativeStrengthIndicatorService relativeStrengthIndicatorService;
    private final NetworkStore networkStore;

    public Double predict(String symbol) {
        var network = networkStore.get("RSI->" + symbol);
        var rsi = relativeStrengthIndicatorService.findLast(symbol);
        var result = network.predict(rsi.getGainSumm(), rsi.getLossSumm()); //Возможно надо будет заменить на отношение Gain к Loss
        return result;
    }

}
