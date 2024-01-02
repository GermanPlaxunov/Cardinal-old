package org.cardinal.data.entities.indicators;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "APO")
public class AbsolutePriceOscillatorEntity implements DateDataItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "SYMBOL")
    private String symbol;
    @Column(name = "VALUE")
    private Double value;
    @Column(name = "DEPTH")
    private Long depth;
    @Column(name = "DATE")
    private LocalDateTime date;

    @Override
    public LocalDateTime getDate() {
        return this.date;
    }

}
