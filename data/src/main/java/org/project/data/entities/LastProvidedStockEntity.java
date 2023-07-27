package org.project.data.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "LAST_PROVIDED_STOCK")
public class LastProvidedStockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "SYMBOL")
    private String symbol;
    @Column(name = "STOCK_ID")
    private Long stockId;
    @Column(name = "STOCK_DATE")
    private LocalDateTime stockDate;
}
