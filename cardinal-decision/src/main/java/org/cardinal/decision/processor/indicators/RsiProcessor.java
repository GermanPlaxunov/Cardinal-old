package org.cardinal.decision.processor.indicators;

import lombok.RequiredArgsConstructor;
import org.project.model.CoreStock;
import org.project.model.Indicators;
import org.project.model.ProcessVars;

@RequiredArgsConstructor
public class RsiProcessor implements IndicatorProcessor {

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
        var prediction = getPrediction(processVars);
        Double score = 0.0;
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
        var prediction = getPrediction(processVars);
        Double score = 0.0;
        if (prediction <= 0) {
            score = 1000.0;
        }
        return score;
    }

    @Override
    public Double getPrediction(ProcessVars processVars) {
        return (double) processVars.getIndicatorsPredictions()
                .get(Indicators.RSI);
    }
}
