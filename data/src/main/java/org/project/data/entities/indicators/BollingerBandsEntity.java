package org.project.data.entities.indicators;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "BBANDS")
public class BollingerBandsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "SYMBOL")
    private String symbol;
    @Column(name = "DATE")
    private LocalDateTime date;
    @Column(name = "DEPTH")
    private Long depth;
    @Column(name = "UPPER")
    private Double upper;
    @Column(name = "MIDDLE")
    private Double middle;
    @Column(name = "LOWER")
    private Double lower;
}
