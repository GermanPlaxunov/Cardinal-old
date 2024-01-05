package org.cardinal.model.indicators;

import lombok.Data;

import java.util.List;

@Data
public class Rsi {

    private Integer numberOfPeriods;
    private Double gainSumm;
    private Double lossSumm;
    private List<Double> gainList;
    private List<Double> lossList;
    private Double relativeStrength;
    private Double rsi;
    private Integer depth;
}
