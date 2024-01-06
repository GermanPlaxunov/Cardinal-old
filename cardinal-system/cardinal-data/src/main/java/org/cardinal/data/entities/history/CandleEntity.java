package org.cardinal.data.entities.history;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "CANDLE")
public class CandleEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "instrument_id")
    private String instrumentId;
    @Column(name = "open")
    private BigDecimal open;
    @Column(name = "low")
    private BigDecimal low;
    @Column(name = "high")
    private BigDecimal high;
    @Column(name = "close")
    private BigDecimal close;
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    @Column(name = "volume")
    private Long volume;
}