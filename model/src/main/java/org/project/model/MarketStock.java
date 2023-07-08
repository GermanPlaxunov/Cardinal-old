package org.project.model;

import lombok.Data;

@Data
public class MarketStock {

    private String symbol;
    private Double open;
    private Double close;
    private Double high;
    private Double low;
    private Double volumeCurr;
    private Double volumeUsd;
}
