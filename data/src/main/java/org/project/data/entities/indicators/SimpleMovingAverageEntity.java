package org.project.data.entities.indicators;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "SMA")
public class SimpleMovingAverageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "SYMBOL")
    private String symbol;
    @Column(name = "DEPTH")
    private Long depth;
    @Column(name = "DATE")
    private LocalDateTime date;
    @Column(name = "VALUE")
    private Double value;
}
