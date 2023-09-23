package org.project.neural.process.predictions.predictors;

import lombok.RequiredArgsConstructor;
import org.project.data.services.interfaces.indicators.AbsolutePriceOscillatorService;
import org.project.model.Indicators;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.predictions.Predictor;

@RequiredArgsConstructor
public class ApoPredictor implements Predictor {

    private final AbsolutePriceOscillatorService absolutePriceOscillatorService;
    private final NetworkStore networkStore;

    @Override
    public Double predict(String symbol) {
        var network = networkStore.get(Indicators.APO, symbol);
        var apo = absolutePriceOscillatorService.findLast(symbol);
        //Пока не понятно на чем основывать предсказание для APO
        var result = network.predict(apo.getValue(), apo.getValue());
        return result;
    }
}
