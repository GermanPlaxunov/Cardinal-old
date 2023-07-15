package org.project.core.database.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "MARKET_DEAL")
public class MarketDealEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "OPEN_DATE")
    private LocalDateTime openDate;
    @Column(name = "CLOSE_DATE")
    private LocalDateTime closeDate;
    @Column(name = "SYMBOLS")
    private String symbols;
    @Column(name = "DEAL_TYPE")
    private String dealType;
    @Column(name = "OPEN_PRICE")
    private Double openPrice;
    @Column(name = "CLOSE_PRICE")
    private Double closePrice;
    @Column(name = "DEAL_AMOUNT_CURR")
    private Double dealAmountCurr;
    @Column(name = "DEAL_AMOUNT_USD")
    private Double dealAmountUsd;
}
