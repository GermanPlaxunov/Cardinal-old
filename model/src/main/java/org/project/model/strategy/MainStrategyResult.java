package org.project.model.strategy;

import lombok.Data;

@Data
public class MainStrategyResult {
    private String symbol;
    private boolean shouldNewPositionBeOpen;
    private boolean shouldCurrentPositionBeClosed;
    private Double amount;
}
