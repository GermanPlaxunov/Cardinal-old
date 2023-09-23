package org.project.neural.process.predictions.predictors;

import lombok.RequiredArgsConstructor;
import org.project.data.services.interfaces.indicators.BollingerBandsService;
import org.project.model.Indicators;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.predictions.Predictor;

@RequiredArgsConstructor
public class BbandPredictor implements Predictor {

    private final BollingerBandsService bollingerBandsService;
    private final NetworkStore networkStore;

    @Override
    public Double predict(String symbol) {
        var network = networkStore.get(Indicators.BBAND, symbol);
        var bband = bollingerBandsService.findLast(symbol);
        //Надо будет поменять
        var prediction = network.predict(bband.getUpper(), bband.getLower());
        return prediction;
    }
}
