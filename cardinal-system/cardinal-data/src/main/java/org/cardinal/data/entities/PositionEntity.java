package org.cardinal.data.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "POSITION",
        indexes = @Index(name = "POSITION_OPEN_DATE_IDX", columnList = "OPEN_DATE, FIGI"))
public class PositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "ACCOUNT_ID")
    private String accountId;
    @Column(name = "FIGI")
    private String figi;
    @Column(name = "CURR_AMT")
    private Double currAmt;
    @Column(name = "OPEN_PRICE")
    private Double openPrice;
    @Column(name = "CLOSE_PRICE")
    private Double closePrice;
    @Column(name = "PROFIT")
    private Double profit;
    @Column(name = "ACCOUNT_BALANCE_BEFORE_OPENING")
    private Double accountBalance;
    @Column(name = "OPEN_COMMISSION")
    private Double openCommission;
    @Column(name = "CLOSE_COMMISSION")
    private Double closeCommission;
    @Column(name = "OPEN_DATE")
    private LocalDateTime openDate;
    @Column(name = "CLOSE_DATE")
    private LocalDateTime closeDate;
    @Column(name = "IS_OPEN")
    private Boolean isOpen;
}
