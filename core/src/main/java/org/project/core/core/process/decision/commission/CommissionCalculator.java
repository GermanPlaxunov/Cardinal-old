package org.project.core.core.process.decision.commission;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.CoreStockEntity;
import org.project.model.ProcessVars;
import org.project.model.decision.commission.CommissionResult;

@RequiredArgsConstructor
public class CommissionCalculator {

    /**
     * Calculates commission size on buying stocks.
     *
     * @param processVars - process data
     * @return commission size
     */
    public CommissionResult calculateCommissionOnBuy(ProcessVars<CoreStockEntity> processVars) {
        return new CommissionResult()
                .setSymbol(processVars.getSymbol())
                .setCommissionPercentageOnBuy(0.001)
                .setCommissionPercentageOnSell(0.001)
                .setCurrencyAmount(1.0)
                .setZeroProfitPrice(processVars.getOpenPrice());
    }

    /**
     * Calculate commission size on selling stokcs.
     *
     * @param processVars - process data
     * @return commission size
     */
    public CommissionResult calculateCommissionOnSell(ProcessVars<CoreStockEntity> processVars) {
        return new CommissionResult()
                .setSymbol(processVars.getSymbol())
                .setCommissionPercentageOnBuy(0.001)
                .setCommissionPercentageOnSell(0.001)
                .setCurrencyAmount(1.0)
                .setZeroProfitPrice(processVars.getOpenPrice());
    }

}
