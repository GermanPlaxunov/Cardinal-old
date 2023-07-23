package org.project.market.database.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "MARKET_STOCK",
        indexes = @Index(name = "MARKET_STOCK_DATE_IDX", columnList = "DATE"))
public class StockEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "SYMBOL")
    private String symbol;
    @Column(name = "OPEN")
    private Double open;
    @Column(name = "CLOSE")
    private Double close;
    @Column(name = "HIGH")
    private Double high;
    @Column(name = "LOW")
    private Double low;
    @Column(name = "VOLUME_CURR")
    private Double volumeCurr;
    @Column(name = "VOLUME_USD")
    private Double volumeUsd;
    @Column(name = "DATE")
    private LocalDateTime date;
}
