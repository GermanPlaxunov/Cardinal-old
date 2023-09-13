package org.project.core.core.process.decision.indicators;

import org.project.core.core.process.vars.ProcessVars;

public abstract class AbstractDecisionProcessor implements IndicatorDecisionProcessor {

    /**
     * Returns values from 0 to 1000
     *
     * @param processVars
     * @return
     */
    public abstract Double analyze(ProcessVars processVars);

}
