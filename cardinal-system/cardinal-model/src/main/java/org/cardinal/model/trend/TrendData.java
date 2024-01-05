package org.cardinal.model.trend;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TrendData {

    private LocalDateTime trendStartDate;
    private LocalDateTime trendEndDate;
    private Double trendStartPrice;
    private Double trendEndPrice;
    private Double priceDeltaOverPeriod;
    private Long periodInSeconds;
    private boolean upTrend;

}
