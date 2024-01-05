package org.cardinal.decision.processor.indicators;

import org.cardinal.model.CoreStock;
import org.cardinal.model.ProcessVars;

public interface IndicatorProcessor {

    Double checkOpenNewPosition(ProcessVars<CoreStock> processVars);

    Double checkCloseCurrentPosition(ProcessVars<CoreStock> processVars);

    Double getPrediction(ProcessVars processVars);

}
