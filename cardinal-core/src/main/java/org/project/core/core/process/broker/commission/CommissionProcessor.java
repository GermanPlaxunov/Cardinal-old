package org.project.core.core.process.broker.commission;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cardinal.data.services.interfaces.ProcessParamsService;
import org.project.model.CoreStock;
import org.project.model.ProcessVars;

@Slf4j
@RequiredArgsConstructor
public class CommissionProcessor {

    private final ProcessParamsService processParamsService;

    /**
     * Calculates commission amount for current operation
     * and set this value to the target field.
     *
     * @param processVars - process data
     */
    public void calculateCommission(ProcessVars<CoreStock> processVars) {
        if (processVars.getIsAnyOpenPosition()) {
            var commission = calculateCommissionOnSell(processVars);
            processVars.setClosePositionCommission(commission);
        } else {
            var commission = calculateCommissionOnBuy(processVars);
            processVars.setOpenPositionCommission(commission);
        }
    }

    /**
     * Calculates commission for opening new position.
     *
     * @param processVars - process data
     * @return commission size
     */
    private Double calculateCommissionOnBuy(ProcessVars<CoreStock> processVars) {
        var symbol = processVars.getSymbol();
        var percent = processParamsService.getBuyCommissionPercentage(symbol);
        var dealPrice = processVars.getCurrentPrice() * processVars.getAmountCurr();
        return dealPrice * percent;
    }

    /**
     * Calculates commission for closing current position.
     *
     * @param processVars - process data
     * @return commission size
     */
    private Double calculateCommissionOnSell(ProcessVars<CoreStock> processVars) {
        var symbol = processVars.getSymbol();
        var percent = processParamsService.getSellCommissionPercentage(symbol);
        var dealPrice = processVars.getCurrentPrice() * processVars.getAmountCurr();
        return dealPrice * percent;
    }

}
