package org.project.core.core.process.decision;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.core.core.process.deal.DealMaker;
import org.project.core.core.process.vars.ProcessVars;
import org.project.data.services.interfaces.PositionService;

@Slf4j
@RequiredArgsConstructor
public class DecisionMakingCenter {

    private final PositionService positionService;
    private final DealMaker dealMaker;

    public void start(ProcessVars processVars) {
        log.trace("DecisionMakingCenter.start");
        var symbol = processVars.getSymbol();
        if (positionService.ifOpenPosition(symbol)) {
            log.info("Checking if current {} positioin should be closed.", symbol);
            // проверка на то, можно ли послать сигнал закрытия сделки
            var closeSignal = shouldPositionBeClosed(processVars);
            if(closeSignal) {
                dealMaker.closeLongPosition(symbol);
            }
        } else {
            log.info("Checking if new {} position should be open.", symbol);
            // проверка на снижение цены с момента закрытия последней сделки
            // или на вероятность выхода из коридора
            var openPosition = shouldPositionBeOpen(processVars);
            if(openPosition) {
                dealMaker.openLongPosition(symbol, 1.0);
            }
        }
    }

    private boolean shouldPositionBeClosed(ProcessVars processVars) {
        var basic = processVars.getBasicStrategyResult();
        if (basic.getClosePositionSignal()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean shouldPositionBeOpen(ProcessVars processVars) {
        var basic = processVars.getBasicStrategyResult();
        if (basic.getOpenPositionsCount() == 0) {
            return true;
        } else {
            return false;
        }
    }

}
