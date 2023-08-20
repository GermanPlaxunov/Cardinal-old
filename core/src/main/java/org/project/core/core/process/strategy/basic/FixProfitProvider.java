package org.project.core.core.process.strategy.basic;

public class FixProfitProvider {

    private final Double fixProfitKoeff = 0.02;

    public Double getFixProfitPrice(Double openPositionPrice) {
        return openPositionPrice + (openPositionPrice * fixProfitKoeff);
    }

    public boolean checkFixProfit(Double openPositionPrice, Double currentPrice) {
        return currentPrice >= (openPositionPrice + (openPositionPrice * fixProfitKoeff));
    }

}
