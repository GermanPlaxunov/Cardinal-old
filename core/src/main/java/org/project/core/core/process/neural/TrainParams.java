package org.project.core.core.process.neural;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TrainParams {
    private String symbol;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;

}
