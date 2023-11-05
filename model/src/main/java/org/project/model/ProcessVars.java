package org.project.model;

import lombok.Data;
import org.project.model.indicators.Bband;
import org.project.model.indicators.Rsi;
import org.project.model.trend.TrendData;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ProcessVars {

    private String symbol;
    private Double amount;
    private Double openPrice;
    private Double currentPrice;
    private Bband bband;
    private Rsi rsi;
    private Double apo;
    private Double ema;
    private Double sma;
    private Double std;
    private Long depth;
    private LocalDateTime date;
    private Map<String, Double> priceChangePredictions;
    private TrendData trendData;
}
