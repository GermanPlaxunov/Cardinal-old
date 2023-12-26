package org.project.neural.process.training.training;

import lombok.Data;
import org.libra.data.entities.CoreStockEntity;
import org.project.model.Indicators;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TrainParams {
    private String symbol;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private List<Double> prices;
    private Indicators indicator;
    private List<CoreStockEntity> stocks;
    private Long epochs;
}
