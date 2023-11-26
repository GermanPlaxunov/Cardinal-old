package org.project.core.core.process.decision;

import lombok.RequiredArgsConstructor;
import org.project.model.decision.DecisionResult;

@RequiredArgsConstructor
public class DecisionStarter {

    /**
     * Returns data about decision and amount of
     * stocks should be bought.
     *
     * @return decision.
     */
    public DecisionResult ifNewPositionShouldBeOpened(String symbol) {
        return new DecisionResult();
    }

    /**
     * Returns data about decision.
     *
     * @return decision.
     */
    public DecisionResult ifCurrentPositionShouldBeClosed(String symbol) {
        return new DecisionResult();
    }

}
