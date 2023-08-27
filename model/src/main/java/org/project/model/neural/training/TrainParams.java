package org.project.model.neural.training;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TrainParams {
    private String symbol;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;

}
