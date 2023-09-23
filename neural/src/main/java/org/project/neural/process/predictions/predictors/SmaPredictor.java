package org.project.neural.process.predictions.predictors;

import lombok.RequiredArgsConstructor;
import org.project.data.services.interfaces.indicators.SimpleMovingAverageService;
import org.project.model.Indicators;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.predictions.Predictor;

@RequiredArgsConstructor
public class SmaPredictor implements Predictor {

    private final SimpleMovingAverageService simpleMovingAverageService;
    private final NetworkStore networkStore;

    @Override
    public Double predict(String symbol) {
        var network = networkStore.get(Indicators.SMA, symbol);
        //Заменить передаваемое значение
        var sma = simpleMovingAverageService.findLast(symbol);
        var prediction = network.predict(sma.getValue(), sma.getValue());
        return prediction;
    }
}
