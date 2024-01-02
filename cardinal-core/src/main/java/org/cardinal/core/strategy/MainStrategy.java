package org.cardinal.core.strategy;

import lombok.RequiredArgsConstructor;
import org.cardinal.data.services.interfaces.PositionService;
import org.project.model.CoreStock;
import org.project.model.ProcessVars;

@RequiredArgsConstructor
public class MainStrategy {

    private final PositionService positionService;

    /**
     * Checks if there is any open positions. If there is
     * checks if it should be closed, otherwise checks for
     * closing existing position. Without neural algorithms.
     *
     * @param processVars - process data
     */
    public void launchStrategy(ProcessVars<CoreStock> processVars) {
        var symbol = processVars.getSymbol();
        if (positionService.ifOpenPosition(symbol)) {
            ifCurrentPositionShouldBeClosed(processVars);
        } else {
            ifNewPositionShouldBeOpened(processVars);
        }
    }

    /**
     * Launches analyze about opening new position.
     *
     * @param processVars - process data
     */
    private void ifNewPositionShouldBeOpened(ProcessVars<CoreStock> processVars) {

    }

    /**
     * Launches analyze about closing current position.
     *
     * @param processVars - process data
     */
    private void ifCurrentPositionShouldBeClosed(ProcessVars<CoreStock> processVars) {

    }

}
