package org.project.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.project.data.entities.indicators.DateDataItem;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "CORE_STOCK")
public class CoreStockEntity implements DateDataItem {

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
