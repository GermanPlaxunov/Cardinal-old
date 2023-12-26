package org.project.core.core.process.decision.indicators;

import lombok.RequiredArgsConstructor;
import org.project.model.CoreStock;
import org.project.model.Indicators;
import org.project.model.ProcessVars;
import org.project.neural.process.NeuralProcessStarter;

@RequiredArgsConstructor
public class EmaProcessor implements IndicatorProcessor {

    private final NeuralProcessStarter neuralProcessStarter;

    /**
     * Returns the score of opening new position. (0-1000)
     * Than closer score to 1000 then stronger decision to open
     * new position and vice versa.
     *
     * @param processVars - process data.
     * @return score
     */
    @Override
    public Double checkOpenNewPosition(ProcessVars<CoreStock> processVars) {
        var symbol = processVars.getSymbol();
        var prediction = neuralProcessStarter.predict(symbol, Indicators.EMA.name());
        var score = 0.0;
        if (prediction > 0) {
            score = 1000.0;
        }
        return score;
    }

    /**
     * Returns the score of closing current position. (0-1000)
     * Than closer score to 1000 then stronger decision to close
     * current position and vice versa.
     *
     * @param processVars - process data.
     * @return score
     */
    @Override
    public Double checkCloseCurrentPosition(ProcessVars<CoreStock> processVars) {
        var symbol = processVars.getSymbol();
        var prediction = neuralProcessStarter.predict(symbol, Indicators.EMA.name());
        var score = 0.0;
        if (prediction <= 0) {
            score = 1000.0;
        }
        return score;
    }
}
