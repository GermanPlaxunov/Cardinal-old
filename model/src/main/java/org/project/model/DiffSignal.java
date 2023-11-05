package org.project.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DiffSignal {

    private String symbol;
    private Double prevPrice;
    private Double currPrice;
    private Double diff;
    private Integer priceDiffSignal;
    private Integer positionSignal;
    private LocalDateTime date;
}
