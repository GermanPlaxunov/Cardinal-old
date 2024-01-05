package org.cardinal.model;

import lombok.Data;
import org.cardinal.model.decision.Decision;
import org.cardinal.model.trend.TrendData;
import org.cardinal.model.indicators.Bband;
import org.cardinal.model.indicators.Rsi;
import org.cardinal.model.strategy.MainStrategyResult;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class ProcessVars<stock> {
    private String symbol;
    private Double amountCurr;
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
    private List<stock> stocks;
    private Double openPositionCommission;
    private Double closePositionCommission;
    private Boolean isAnyOpenPosition;
    private Double score;
    private Map<Indicators, Double> indicatorsPredictions;
    private Decision decision;
    private stock lastProvidedStock;
    private MainStrategyResult mainStrategyResult;
}
