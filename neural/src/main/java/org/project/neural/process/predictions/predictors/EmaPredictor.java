package org.project.neural.process.predictions.predictors;

import lombok.RequiredArgsConstructor;
import org.project.data.services.interfaces.indicators.ExponentialMovingAverageService;
import org.project.model.Indicators;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.predictions.Predictor;

@RequiredArgsConstructor
public class EmaPredictor implements Predictor {

    private final ExponentialMovingAverageService exponentialMovingAverageService;
    private final NetworkStore networkStore;

    @Override
    public Double predict(String symbol) {
        var network = networkStore.get(Indicators.EMA, symbol);
        var ema = exponentialMovingAverageService.findLast(symbol);
        //Поменять на другие значения
        var result = network.predict(ema.getValue(), ema.getValue());
        return result;
    }
}
