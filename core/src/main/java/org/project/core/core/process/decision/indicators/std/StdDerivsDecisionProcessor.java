package org.project.core.core.process.decision.indicators.std;

import lombok.RequiredArgsConstructor;
import org.project.core.client.NeuralClient;
import org.project.core.core.process.decision.indicators.IndicatorDecisionProcessor;
import org.project.core.core.process.vars.ProcessVars;
import org.project.model.Indicators;

@RequiredArgsConstructor
public class StdDerivsDecisionProcessor implements IndicatorDecisionProcessor {

    private final StdPredictionProcessor stdPredictionProcessor;
    private final NeuralClient neuralClient;

    /**
     * Returns a score in favor of closing current
     * position according to current STD indicator
     * value.
     *
     * @param processVars - process data.
     * @return score
     */
    @Override
    public Long shouldPositionBeClosed(ProcessVars processVars) {
        var symbol = processVars.getSymbol();
        var priceChangePrediction = neuralClient.predict(symbol, Indicators.STD.name());
        var score = stdPredictionProcessor.checkToCloseCurrentPosition(priceChangePrediction);
        return score;
    }

    /**
     * Returns a score in favor of opening new
     * position according to current STD indicator
     * value.
     *
     * @param processVars - process data.
     * @return score
     */
    @Override
    public Long shouldPositionBeOpen(ProcessVars processVars) {
        var symbol = processVars.getSymbol();
        var priceChangePrediction = neuralClient.predict(symbol, Indicators.STD.name());
        var score = stdPredictionProcessor.checkToOpenNewPosition(priceChangePrediction);
        return score;
    }
}
