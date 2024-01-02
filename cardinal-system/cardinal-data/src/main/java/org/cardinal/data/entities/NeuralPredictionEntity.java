package org.cardinal.data.entities;

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
    @Column(name = "ID")
    private Long id;
    @Column(name = "SYMBOL")
    private String symbol;
    @Column(name = "INDICATOR_NAME")
    private String indicatorName;
    @Column(name = "INDICATOR_VALUE")
    private Double indicatorValue;
    @Column(name = "CURRENT_PRICE")
    private Double currentPrice;
    @Column(name = "PREV_TO_CURR_PRICE_DELTA")
    private Double prevToCurrPriceDelta;
    @Column(name = "PRICE_CHANGE_PREDICTION")
    private Double priceChangePrediction;
    @Column(name = "DATE")
    private LocalDateTime date;
}
