package org.project.model.decision.commission;

import lombok.Data;

@Data
public class CommissionResult {

    private String symbol;
    private Double currencyAmount;
    private Double commissionPercentageOnBuy;
    private Double commissionPercentageOnSell;
    private Double zeroProfitPrice;

}
