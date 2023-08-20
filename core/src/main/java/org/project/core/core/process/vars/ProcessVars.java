package org.project.core.core.process.vars;

import lombok.Data;
import org.project.core.core.process.indicators.model.Bband;
import org.project.core.core.process.indicators.model.Rsi;

@Data
public class ProcessVars {

    private String symbol;
    private Double amount;
    private Double openPrice;
    private Double currentPrice;
    private Bband bband;
    private Rsi rsi;
    private Double apo;
    private Double ema;
    private Double sma;
    private Double std;
    private BasicStrategyResult basicStrategyResult;
}
