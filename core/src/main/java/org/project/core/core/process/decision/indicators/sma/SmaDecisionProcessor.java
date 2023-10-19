package org.project.core.core.process.decision.indicators.sma;

import lombok.RequiredArgsConstructor;
import org.project.core.client.NeuralClient;
import org.project.core.core.process.decision.indicators.IndicatorDecisionProcessor;
import org.project.core.core.process.vars.ProcessVars;
import org.project.model.Indicators;

@RequiredArgsConstructor
public class SmaDecisionProcessor implements IndicatorDecisionProcessor {

    private final SmaPredictionProcessor smaPredictionProcessor;
    private final NeuralClient neuralClient;

    /**
     * Returns a score in favor of closing current
     * position according to current SMA indicator
     * value.
     *
     * @param processVars - process data.
     * @return score
     */
    @Override
    public Long shouldPositionBeClosed(ProcessVars processVars) {
        var symbol = processVars.getSymbol();
        var priceChangePrediction = neuralClient.predict(symbol, Indicators.SMA.name());
        var score = smaPredictionProcessor.checkToCloseCurrentPosition(priceChangePrediction);
        return score;
    }

    /**
     * Returns a score in favor of opening new
     * position according to current SMA indicator
     * value.
     *
     * @param processVars - process data.
     * @return score
     */
    @Override
    public Long shouldPositionBeOpen(ProcessVars processVars) {
        var symbol = processVars.getSymbol();
        var priceChangePrediction = neuralClient.predict(symbol, Indicators.SMA.name());
        var score = smaPredictionProcessor.checkToOpenNewPosition(priceChangePrediction);
        return score;
    }
}
