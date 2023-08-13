package org.project.core.core.process.vars;

import lombok.Data;

@Data
public class DiffStrategyResult {

    private String symbol;
    private Double amount;
    private Boolean openPosition;
    private Boolean closePosition;
    private Boolean doNothing;
}
