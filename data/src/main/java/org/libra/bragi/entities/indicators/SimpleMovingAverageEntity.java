package org.libra.bragi.entities.indicators;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "SMA")
public class SimpleMovingAverageEntity implements DateDataItem {

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

    @Override
    public LocalDateTime getDate() {
        return this.date;
    }

}
