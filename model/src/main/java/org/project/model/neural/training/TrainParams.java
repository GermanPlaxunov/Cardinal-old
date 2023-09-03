package org.project.model.neural.training;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TrainParams {
    private String symbol;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private List<Double> prices;
}
