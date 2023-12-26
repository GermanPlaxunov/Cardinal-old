package org.project.core.core.process.decision.indicators;

import org.project.model.CoreStock;
import org.project.model.ProcessVars;

public interface IndicatorProcessor {

    Double checkOpenNewPosition(ProcessVars<CoreStock> processVars);

    Double checkCloseCurrentPosition(ProcessVars<CoreStock> processVars);

}
