package org.project.core.core.process.decision.indicators;

import org.project.data.entities.CoreStockEntity;
import org.project.model.ProcessVars;

public interface IndicatorProcessor {

    Double openNewPosition(ProcessVars<CoreStockEntity> processVars);

    Double closeCurrentPosition(ProcessVars<CoreStockEntity> processVars);

}
