package org.project.core.core.process.vars;

import lombok.Data;

@Data
public class BasicStrategyResult {

    private String symbol;
    private Double amount;
    private Double positionOpenPrice;
    private Double fixProfitPrice;
    private Double stopLossPrice;
    private Double currentPrice;
    private Integer openPositionsCount;
    private Boolean closePositionSignal;
    private Boolean openPositionSignal;
}
