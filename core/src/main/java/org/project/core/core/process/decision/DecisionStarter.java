package org.project.core.core.process.decision;

import lombok.RequiredArgsConstructor;
import org.project.core.core.process.decision.indicators.IndicatorProcessorsStore;
import org.project.data.entities.CoreStockEntity;
import org.project.model.Indicators;
import org.project.model.ProcessVars;
import org.project.model.decision.DecisionResult;

@RequiredArgsConstructor
public class DecisionStarter {

    private final IndicatorProcessorsStore indicatorProcessorsStore;

    /**
     * Returns data about decision and amount of
     * stocks should be bought.
     *
     * @return decision.
     */
    public DecisionResult ifNewPositionShouldBeOpened(ProcessVars<CoreStockEntity> processVars) {
        Double finalScore = 0.0;
        for(var indicator : Indicators.values()){
            var processor = indicatorProcessorsStore.get(indicator);
            finalScore += processor.checkOpenNewPosition(processVars);
        }
        return new DecisionResult();
    }

    /**
     * Returns decision whether current position
     * should be closed.
     *
     * @return decision.
     */
    public DecisionResult ifCurrentPositionShouldBeClosed(ProcessVars<CoreStockEntity> processVars) {
        Double finalScore = 0.0;
        for(var indicator : Indicators.values()){
            var processor = indicatorProcessorsStore.get(indicator);
            finalScore += processor.checkCloseCurrentPosition(processVars);
        }
        return new DecisionResult();
    }

}
