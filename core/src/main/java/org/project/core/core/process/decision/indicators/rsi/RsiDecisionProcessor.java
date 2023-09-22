package org.project.core.core.process.decision.indicators.rsi;

import lombok.RequiredArgsConstructor;
import org.project.core.client.NeuralClient;
import org.project.core.core.process.decision.indicators.IndicatorDecisionProcessor;
import org.project.core.core.process.vars.ProcessVars;

@RequiredArgsConstructor
public class RsiDecisionProcessor implements IndicatorDecisionProcessor {

    private final RsiPredictionProcessor rsiPredictionProcessor;
    private final NeuralClient neuralClient;

    @Override
    public Long shouldPositionBeClosed(ProcessVars processVars) {
        var symbol = processVars.getSymbol();
        var priceChangePrediction = neuralClient.predict(symbol);
        var score = rsiPredictionProcessor.checkToCloseCurrentPosition(priceChangePrediction);
        return score;
    }

    @Override
    public Long shouldPositionBeOpen(ProcessVars processVars) {
        var symbol = processVars.getSymbol();
        var priceChangePrediction = neuralClient.predict(symbol);
        var score = rsiPredictionProcessor.checkToOpenNewPosition(priceChangePrediction);
        return score;
    }
}
