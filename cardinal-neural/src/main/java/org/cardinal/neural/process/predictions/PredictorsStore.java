package org.cardinal.neural.process.predictions;

import org.cardinal.neural.process.predictions.predictors.*;
import org.project.model.Indicators;

import java.util.HashMap;

public class PredictorsStore {

    private final HashMap<Indicators, Predictor> predictors;

    public PredictorsStore(ApoPredictor apoPredictor,
                           BbandPredictor bbandPredictor,
                           EmaPredictor emaPredictor,
                           RsiPredictor rsiPredictor,
                           SmaPredictor smaPredictor,
                           StdPredictor stdPredictor) {
        predictors = new HashMap<>();
        predictors.put(Indicators.APO, apoPredictor);
        predictors.put(Indicators.BBAND, bbandPredictor);
        predictors.put(Indicators.EMA, emaPredictor);
        predictors.put(Indicators.RSI, rsiPredictor);
        predictors.put(Indicators.SMA, smaPredictor);
        predictors.put(Indicators.STD, stdPredictor);
    }

    public Predictor get(Indicators indicator) {
        return predictors.get(indicator);
    }

}
