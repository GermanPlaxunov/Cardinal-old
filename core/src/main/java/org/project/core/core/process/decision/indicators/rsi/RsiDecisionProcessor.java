package org.project.core.core.process.decision.indicators.rsi;

import lombok.RequiredArgsConstructor;
import org.project.core.client.NeuralClient;
import org.project.core.core.process.decision.indicators.AbstractDecisionProcessor;
import org.project.core.core.process.vars.ProcessVars;

@RequiredArgsConstructor
public class RsiDecisionProcessor extends AbstractDecisionProcessor {

    private final NeuralClient neuralClient;

    @Override
    public Double analyze(ProcessVars processVars) {
        //Тут всякая логика включая вызов neural
        var symbol = processVars.getSymbol();
        var neuralResult = neuralClient.predict(symbol);

        return null;
    }

}
