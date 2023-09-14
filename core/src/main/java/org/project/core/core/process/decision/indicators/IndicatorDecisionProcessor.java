package org.project.core.core.process.decision.indicators;

import org.project.core.core.process.vars.ProcessVars;

public interface IndicatorDecisionProcessor {

    Long shouldPositionBeClosed(ProcessVars processVars);

    Long shouldPositionBeOpen(ProcessVars processVars);

}
