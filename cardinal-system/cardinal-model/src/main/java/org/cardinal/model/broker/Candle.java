package org.cardinal.model.broker;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Candle {

    private String figi;
    private BigDecimal open;
    private BigDecimal low;
    private BigDecimal high;
    private BigDecimal close;
    private LocalDateTime dateTime;
    private Long volume;

}
