package org.project.core.core.process.decision.indicators;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.CoreStockEntity;
import org.project.model.ProcessVars;

@RequiredArgsConstructor
public class EmaProcessor implements IndicatorProcessor {

    /**
     * Returns the score of opening new position. (0-1000)
     * Than closer score to 1000 then stronger decision to open
     * new position and vice versa.
     *
     * @param processVars - process data.
     * @return score
     */
    @Override
    public Double openNewPosition(ProcessVars<CoreStockEntity> processVars) {
        return null;
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
    public Double closeCurrentPosition(ProcessVars<CoreStockEntity> processVars) {
        return null;
    }
}
