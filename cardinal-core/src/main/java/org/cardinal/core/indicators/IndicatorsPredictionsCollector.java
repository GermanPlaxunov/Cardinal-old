package org.cardinal.core.indicators;

import lombok.RequiredArgsConstructor;
import org.project.model.Indicators;
import org.project.model.ProcessVars;
import org.cardinal.neural.process.NeuralProcessStarter;

import java.util.HashMap;

@RequiredArgsConstructor
public class IndicatorsPredictionsCollector {

    private final NeuralProcessStarter neuralProcessStarter;

    /**
     * Collects neural predictions for all indicators, stores them in hashMap
     * and sets result map in processVars.
     *
     * @param processVars - process data
     */
    public void collectPredictions(ProcessVars processVars) {
        var symbol = processVars.getSymbol();
        var predictions = new HashMap<Indicators, Double>();
        for (var indicator : Indicators.values()) {
            var prediction = neuralProcessStarter.predict(symbol, indicator.name());
            predictions.put(indicator, prediction);
        }
        processVars.setIndicatorsPredictions(predictions);
    }

}
