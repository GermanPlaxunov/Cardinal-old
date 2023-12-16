package org.project.core.core.process.decision.indicators;

import lombok.RequiredArgsConstructor;
import org.project.core.client.NeuralClient;
import org.project.data.entities.CoreStockEntity;
import org.project.model.Indicators;
import org.project.model.ProcessVars;

@RequiredArgsConstructor
public class ApoProcessor implements IndicatorProcessor {

    private final NeuralClient neuralClient;

    /**
     * Returns the score of opening new position. (0-1000)
     * Than closer score to 1000 then stronger decision to open
     * new position and vice versa.
     *
     * @param processVars - process data.
     * @return score
     */
    @Override
    public Double checkOpenNewPosition(ProcessVars<CoreStockEntity> processVars) {
        var symbol = processVars.getSymbol();
        var prediction = neuralClient.predict(symbol, Indicators.APO.name());
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
    public Double checkCloseCurrentPosition(ProcessVars<CoreStockEntity> processVars) {
        var symbol = processVars.getSymbol();
        var prediction = neuralClient.predict(symbol, Indicators.APO.name());
        Double score = 0.0;
        if (prediction <= 0) {
            score = 1000.0;
        }
        return score;
    }
}
