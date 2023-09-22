package org.project.core.core.process.decision.indicators.bband;

import lombok.RequiredArgsConstructor;
import org.project.core.client.NeuralClient;
import org.project.core.core.process.decision.indicators.IndicatorDecisionProcessor;
import org.project.core.core.process.vars.ProcessVars;

@RequiredArgsConstructor
public class BbandDecisionProcessor implements IndicatorDecisionProcessor {

    private final BbandPredictionProcessor bbandPredictionProcessor;
    private final NeuralClient neuralClient;

    @Override
    public Long shouldPositionBeClosed(ProcessVars processVars) {
        var symbol = processVars.getSymbol();
        var priceChangePrediction = neuralClient.predict(symbol);
        var score = bbandPredictionProcessor.checkToCloseCurrentPosition(priceChangePrediction);
        return score;
    }

    @Override
    public Long shouldPositionBeOpen(ProcessVars processVars) {
        var symbol = processVars.getSymbol();
        var priceChangePrediction = neuralClient.predict(symbol);
        var score = bbandPredictionProcessor.checkToOpenNewPosition(priceChangePrediction);
        return score;
    }
}
