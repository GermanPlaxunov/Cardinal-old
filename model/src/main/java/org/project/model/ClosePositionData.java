package org.project.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClosePositionData {

    private String symbol;
    private Double amountCurr;
    private Double profit;
    private Double account;
    private Double closePrice;
    private LocalDateTime closeDate;

}
