package org.project.core.core.process.decision.indicators;

import org.project.core.core.process.vars.ProcessVars;

public interface IndicatorDecisionProcessor {

    /**
     * Returns the score of current indicator value and prediction
     * for it (0 - 1000). If score is higher than 500 that means
     * position should be closed. Then higher the score then stronger
     * decision.
     *
     * @param processVars - process data.
     * @return decision score.
     */
    Long shouldPositionBeClosed(ProcessVars processVars);

    /**
     * Returns the score of current indicator value and prediction
     * for it (0 - 1000). If score is higher than 500 that means
     * position should be open. Then higher the score then stronger
     * decision.
     *
     * @param processVars - process data.
     * @return decision score.
     */
    Long shouldPositionBeOpen(ProcessVars processVars);

}
