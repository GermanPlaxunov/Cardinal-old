package org.project.core.core.process.decision.indicators.std;

import lombok.RequiredArgsConstructor;
import org.project.core.client.NeuralClient;
import org.project.core.core.process.decision.indicators.IndicatorDecisionProcessor;
import org.project.core.core.process.vars.ProcessVars;

@RequiredArgsConstructor
public class StdDerivsDecisionProcessor implements IndicatorDecisionProcessor {

    private final StdPredictionProcessor stdPredictionProcessor;
    private final NeuralClient neuralClient;

    @Override
    public Long shouldPositionBeClosed(ProcessVars processVars) {
        var symbol = processVars.getSymbol();
        var priceChangePrediction = neuralClient.predict(symbol);
        var score = stdPredictionProcessor.checkToCloseCurrentPosition(priceChangePrediction);
        return score;
    }

    @Override
    public Long shouldPositionBeOpen(ProcessVars processVars) {
        var symbol = processVars.getSymbol();
        var priceChangePrediction = neuralClient.predict(symbol);
        var score = stdPredictionProcessor.checkToOpenNewPosition(priceChangePrediction);
        return score;
    }
}
