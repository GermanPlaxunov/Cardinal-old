package org.project.core.core.process.decision.indicators.bband;

import lombok.RequiredArgsConstructor;
import org.project.core.client.NeuralClient;
import org.project.core.core.process.decision.indicators.IndicatorDecisionProcessor;
import org.project.core.core.process.vars.ProcessVars;
import org.project.model.Indicators;

@RequiredArgsConstructor
public class BbandDecisionProcessor implements IndicatorDecisionProcessor {

    private final BbandPredictionProcessor bbandPredictionProcessor;
    private final NeuralClient neuralClient;

    /**
     * Returns a score in favor of closing current
     * position according to current BBAND indicator
     * value.
     *
     * @param processVars - process data.
     * @return score
     */
    @Override
    public Long shouldPositionBeClosed(ProcessVars processVars) {
        var symbol = processVars.getSymbol();
        var priceChangePrediction = neuralClient.predict(symbol, Indicators.BBAND.name());
        var score = bbandPredictionProcessor.checkToCloseCurrentPosition(priceChangePrediction);
        return score;
    }

    /**
     * Returns a score in favor of opening new
     * position according to current BBAND indicator
     * value.
     *
     * @param processVars - process data.
     * @return score
     */
    @Override
    public Long shouldPositionBeOpen(ProcessVars processVars) {
        var symbol = processVars.getSymbol();
        var priceChangePrediction = neuralClient.predict(symbol, Indicators.BBAND.name());
        var score = bbandPredictionProcessor.checkToOpenNewPosition(priceChangePrediction);
        return score;
    }
}
