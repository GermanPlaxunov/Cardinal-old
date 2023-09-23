package org.project.core.core.process.decision;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.core.core.process.deal.DealMaker;
import org.project.core.core.process.decision.indicators.DecisionProcessorsStore;
import org.project.core.core.process.decision.indicators.rsi.RsiDecisionProcessor;
import org.project.core.core.process.vars.ProcessVars;
import org.project.data.services.interfaces.PositionService;
import org.project.model.IndicatorType;

@Slf4j
@RequiredArgsConstructor
public class DecisionMakingCenter {

    private final DecisionProcessorsStore decisionProcessorsStore;
    private final PositionService positionService;
    private final DealMaker dealMaker;

    /**
     * Checking if there is any open positions and if it is, verifying it
     * whether it should be closed or not.
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

    private boolean shouldPositionBeClosed(ProcessVars processVars) {
        var processor = decisionProcessorsStore.get(IndicatorType.INDICATOR_TYPE_RSI);
        return processor.shouldPositionBeClosed(processVars) > 0;
    }

    private boolean shouldPositionBeOpen(ProcessVars processVars) {
        var processor = decisionProcessorsStore.get(IndicatorType.INDICATOR_TYPE_RSI);
        return processor.shouldPositionBeOpen(processVars) > 0;
    }

}
