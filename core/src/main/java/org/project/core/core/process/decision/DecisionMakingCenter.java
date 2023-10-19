package org.project.core.core.process.decision;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.core.core.process.deal.DealMaker;
import org.project.core.core.process.decision.indicators.DecisionProcessorsStore;
import org.project.core.core.process.vars.ProcessVars;
import org.project.data.services.interfaces.PositionService;
import org.project.model.Indicators;

@Slf4j
@RequiredArgsConstructor
public class DecisionMakingCenter {

    private final DecisionProcessorsStore decisionProcessorsStore;
    private final PositionService positionService;
    private final DealMaker dealMaker;

    /**
     * The entrypoint of decision centre. If there is any open position,
     * check whether it`s time to close it. Otherwise, check if the
     * situation is good enough to open new position.
     *
     * @param processVars - a set of indicators and useful values.
     */
    public void start(ProcessVars processVars) {
        log.trace("DecisionMakingCenter.start");
        var symbol = processVars.getSymbol();
        if (positionService.ifOpenPosition(symbol)) {
            log.info("Check if open {} position should be closed", symbol);
            var closeSignal = shouldPositionBeClosed(processVars);
            if (closeSignal) {
                dealMaker.closeLongPosition(symbol);
            }
        } else {
            log.info("Checking if new {} position should be open.", symbol);
            var openPosition = shouldPositionBeOpen(processVars);
            if (openPosition) {
                dealMaker.openLongPosition(symbol, 1.0);
            }
        }
    }

    /**
     * Dummy logic. Should decide whether the position
     * should be closed according to the values returned
     * by all processors.
     *
     * @param processVars - process data.
     * @return final decision.
     */
    private boolean shouldPositionBeClosed(ProcessVars processVars) {
        var totalScore = 0.0;
        for(var indicator : Indicators.values()) {
            var processor = decisionProcessorsStore.get(indicator);
            totalScore += processor.shouldPositionBeClosed(processVars);
        }
        return totalScore > 1000;
    }

    /**
     * Dummy logic. Should decide whether the position
     * should be open according to the values returned
     * by all processors.
     *
     * @param processVars - process data.
     * @return final decision.
     */
    private boolean shouldPositionBeOpen(ProcessVars processVars) {
        var totalScore = 0.0;
        for(var indicator : Indicators.values()) {
            var processor = decisionProcessorsStore.get(indicator);
            totalScore += processor.shouldPositionBeOpen(processVars);
        }
        return totalScore > 1000;
    }

}
