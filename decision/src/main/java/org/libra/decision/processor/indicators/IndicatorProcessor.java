package org.libra.decision.processor.indicators;

import org.project.model.CoreStock;
import org.project.model.ProcessVars;

public interface IndicatorProcessor {

    Double checkOpenNewPosition(ProcessVars<CoreStock> processVars);

    Double checkCloseCurrentPosition(ProcessVars<CoreStock> processVars);

    Double getPrediction(ProcessVars processVars);

}
