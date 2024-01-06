package org.cardinal.data.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "share")
public class ShareEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "figi")
    private String figi;
    @Column(name = "api_trade_available_flag")
    private Boolean apiTradeAvailableFlag;
    @Column(name = "buy_available_flag")
    private Boolean buyAvailableFlag;
    @Column(name = "blocked_tca_flag")
    private Boolean blockedTcaFlag;
    @Column(name = "class_code")
    private String classCode;
    @Column(name = "country_of_risk")
    private String countryOfRisk;
    @Column(name = "currency")
    private String currency;
    @Column(name = "div_yield_flag")
    private Boolean divYieldFlag;
    @Column(name = "exchange")
    private String exchange;
    @Column(name = "ticker")
    private String ticker;

    //TODO: Дополнить остальными полями

}
