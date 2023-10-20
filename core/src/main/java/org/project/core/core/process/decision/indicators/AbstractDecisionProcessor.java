package org.project.core.core.process.decision.indicators;

import org.project.core.core.process.vars.ProcessVars;

public abstract class AbstractDecisionProcessor implements IndicatorDecisionProcessor {

    public abstract Long shouldPositionBeClosed(ProcessVars processVars);

    public abstract Long shouldPositionBeOpen(ProcessVars processVars);

}
