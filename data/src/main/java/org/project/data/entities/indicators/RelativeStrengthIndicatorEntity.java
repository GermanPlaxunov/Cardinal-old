package org.project.data.entities.indicators;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "RSI")
public class RelativeStrengthIndicatorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "SYMBOL")
    private String symbol;
    @Column(name = "DATE")
    private LocalDateTime date;
    @Column(name = "RSI")
    private Double rsi;
    @Column(name = "DEPTH")
    private Long depth;
    @Column(name = "GAIN_SUMM")
    private Double gainSumm;
    @Column(name = "LOSS_SUMM")
    private Double lossSumm;
}
