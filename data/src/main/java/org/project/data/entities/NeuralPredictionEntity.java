package org.project.data.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "NEURAL_PREDICTION",
        indexes = @Index(name = "NEURAL_PREDICTION_DATE_IDX", columnList = "DATE"))
public class NeuralPredictionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID",
            columnDefinition = "Column Id")
    private Long id;
    @Column(name = "SYMBOL",
            columnDefinition = "The name of the stock")
    private String symbol;
    @Column(name = "INDICATOR_NAME",
            columnDefinition = "The name of the indicator")
    private String indicatorName;
    @Column(name = "INDICATOR_VALUE",
            columnDefinition = "The current value of the indicator")
    private Double indicatorValue;
    @Column(name = "CURRENT_PRICE",
            columnDefinition = "The current price of the stock")
    private Double currentPrice;
    @Column(name = "PRICE_DELTA",
            columnDefinition = "The difference between current and previous price")
    private Double priceDelta;
    @Column(name = "PRICE_CHANGE_PREDICTION",
            columnDefinition = "The prediction of price change for the next stock data item")
    private Double priceChangePrediction;
    @Column(name = "DATE",
            columnDefinition = "The date of current stock")
    private LocalDateTime date;
}
