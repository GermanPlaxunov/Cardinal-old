package org.project.core.core.process.strategy.basic;

public class StopLossProvider {

    private final Double stopLossKoeff = 0.07;

    public Double getStopLossPrice(Double openPositionPrice) {
        return openPositionPrice - (openPositionPrice * stopLossKoeff);
    }

    public boolean checkStopLoss(Double openPositionPrice, Double currentPrice) {
        return currentPrice <= (openPositionPrice - (openPositionPrice * stopLossKoeff));
    }

}
