package org.project.core.core.process.strategy;

import lombok.RequiredArgsConstructor;
import org.project.core.core.process.decision.DecisionStarter;
import org.project.model.CoreStock;
import org.project.model.ProcessVars;
import org.project.model.decision.Decision;
import org.project.model.decision.DecisionResult;
import org.project.model.strategy.MainStrategyResult;

@RequiredArgsConstructor
public class MainStrategy {

    private final DecisionStarter decisionStarter;

    /**
     * Check if new position should be opened.
     *
     * @param processVars - process data.
     * @return final decision.
     */
    public MainStrategyResult ifNewPositionShouldBeOpened(ProcessVars<CoreStock> processVars) {
        var decision = decisionStarter.ifNewPositionShouldBeOpened(processVars);
        return new MainStrategyResult()
                .setSymbol(processVars.getSymbol())
                .setShouldNewPositionBeOpen(shouldNewPositionBeOpened(decision))
                .setAmount(decision.getBuyAmountCurr());
    }

    /**
     * Check if current position should be closed.
     *
     * @param processVars - process data.
     * @return final decision.
     */
    public MainStrategyResult ifCurrentPositionShouldBeClosed(ProcessVars<CoreStock> processVars) {
        var decision = decisionStarter.ifCurrentPositionShouldBeClosed(processVars);
        return new MainStrategyResult()
                .setSymbol(processVars.getSymbol())
                .setShouldCurrentPositionBeClosed(shouldCurrPositionBeClosed(decision));
    }

    private boolean shouldCurrPositionBeClosed(DecisionResult decisionResult) {
        return decisionResult.getDecision() == Decision.DECISION_CLOSE_CURRENT;
    }

    private boolean shouldNewPositionBeOpened(DecisionResult decisionResult) {
        return decisionResult.getDecision() == Decision.DECISION_OPEN_NEW;
    }
}
