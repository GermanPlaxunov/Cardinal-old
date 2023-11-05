package org.project.core.core.process.strategy;

import lombok.RequiredArgsConstructor;
import org.project.model.ProcessVars;
import org.project.model.strategy.MainStrategyResult;

@RequiredArgsConstructor
public class MainStrategy {

    /**
     * Check if new position should be opened.
     *
     * @param processVars - process data.
     * @return final decision.
     */
    public MainStrategyResult ifNewPositionShouldBeOpened(ProcessVars processVars) {
        return new MainStrategyResult();
    }

    /**
     * Check if current position should be closed.
     *
     * @param processVars - process data.
     * @return final decision.
     */
    public MainStrategyResult ifCurrentPositionShouldBeClosed(ProcessVars processVars) {
        return new MainStrategyResult();
    }

}
