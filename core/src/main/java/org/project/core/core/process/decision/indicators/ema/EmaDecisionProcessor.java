package org.project.core.core.process.decision.indicators.ema;

import lombok.RequiredArgsConstructor;
import org.project.core.client.NeuralClient;
import org.project.core.core.process.decision.indicators.AbstractDecisionProcessor;
import org.project.core.core.process.vars.ProcessVars;
import org.project.model.Indicators;

@RequiredArgsConstructor
public class EmaDecisionProcessor extends AbstractDecisionProcessor {

    private final EmaPredictionProcessor emaPredictionProcessor;
    private final NeuralClient neuralClient;

    /**
     * Returns a score in favor of closing current
     * position according to current EMA indicator
     * value.
     *
     * @param processVars - process data.
     * @return score
     */
    @Override
    public Long shouldPositionBeClosed(ProcessVars processVars) {
        var symbol = processVars.getSymbol();
        var priceChangePrediction = neuralClient.predict(symbol, Indicators.EMA.name());
        var score = emaPredictionProcessor.checkToCloseCurrentPosition(priceChangePrediction);
        return score;
    }

    /**
     * Returns a score in favor of opening new
     * position according to current EMA indicator
     * value.
     *
     * @param processVars - process data.
     * @return score
     */
    @Override
    public Long shouldPositionBeOpen(ProcessVars processVars) {
        var symbol = processVars.getSymbol();
        var priceChangePrediction = neuralClient.predict(symbol, Indicators.EMA.name());
        var score = emaPredictionProcessor.checkToOpenNewPosition(priceChangePrediction);
        return score;
    }
}
