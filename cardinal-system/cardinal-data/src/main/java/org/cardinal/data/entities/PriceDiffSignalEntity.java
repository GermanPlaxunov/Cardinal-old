package org.cardinal.data.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PRICE_DIFF_SIGNAL")
public class PriceDiffSignalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "SYMBOL")
    private String symbol;
    @Column(name = "PREV_PRICE")
    private Double prevPrice;
    @Column(name = "CURR_PRICE")
    private Double currPrice;
    @Column(name = "DIFF")
    private Double diff;
    @Column(name = "DIFF_SIGNAL")
    private Integer diffSignal;
    @Column(name = "POSITION_SIGNAL")
    private Integer positionSignal;
    @Column(name = "DATE")
    private LocalDateTime date;
}
