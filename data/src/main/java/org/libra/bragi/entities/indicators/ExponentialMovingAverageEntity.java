package org.libra.bragi.entities.indicators;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "EMA")
public class ExponentialMovingAverageEntity implements DateDataItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "SYMBOL")
    private String symbol;
    @Column(name = "DEPTH")
    private Long depth;
    @Column(name = "VALUE")
    private Double value;
    @Column(name = "DATE")
    private LocalDateTime date;

    @Override
    public LocalDateTime getDate() {
        return this.date;
    }

}
