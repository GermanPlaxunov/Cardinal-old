package org.project.neural.process.predictions.predictors;

import lombok.RequiredArgsConstructor;
import org.project.data.services.interfaces.indicators.StandardDerivativesService;
import org.project.model.Indicators;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.predictions.Predictor;

@RequiredArgsConstructor
public class StdPredictor implements Predictor {

    private final StandardDerivativesService standardDerivativesService;
    private final NetworkStore networkStore;

    @Override
    public Double predict(String symbol) {
        var network = networkStore.get(Indicators.STD, symbol);
        var std = standardDerivativesService.findLast(symbol);
        //Заменить передаваемое значение
        var prediction = network.predict(std.getValue(), std.getValue());
        return prediction;
    }
}
