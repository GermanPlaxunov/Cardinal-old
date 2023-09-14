package org.project.core.core.process.decision.indicators.rsi;

import lombok.RequiredArgsConstructor;
import org.project.core.client.NeuralClient;
import org.project.core.core.process.decision.indicators.IndicatorDecisionProcessor;
import org.project.core.core.process.vars.ProcessVars;
import org.project.data.services.interfaces.PositionService;

@RequiredArgsConstructor
public class RsiDecisionProcessor implements IndicatorDecisionProcessor {

    private final PositionService positionService;
    private final NeuralClient neuralClient;

    @Override
    public Long shouldPositionBeClosed(ProcessVars processVars) {
        var symbol = processVars.getSymbol();
        var priceChangePrediction = neuralClient.predict(symbol);
        var score = checkToCloseCurrentPosition(priceChangePrediction);
        return score;
    }

    @Override
    public Long shouldPositionBeOpen(ProcessVars processVars) {
        var symbol = processVars.getSymbol();
        var priceChangePrediction = neuralClient.predict(symbol);
        var score = checkToOpenNewPosition(priceChangePrediction);
        return score;
    }

    /**
     * Dummy logic.
     */
    private Long checkToOpenNewPosition(Double priceChangePrediction) {
        if (priceChangePrediction > 0) {
            return 1000L;
        }
        return 0L;
    }

    /**
     * Dummy logic.
     */
    private Long checkToCloseCurrentPosition(Double priceChangePrediction) {
        if(priceChangePrediction < 0) {
            return 1000L;
        }
        return 0L;
    }
}
