package org.project.market.database.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ACCOUNT",
        indexes = @Index(name = "ACCOUNT_ID_IDX", columnList = "ACCOUNT_ID"))
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "ACCOUNT_ID")
    private String accountId;
    @Column(name = "BALANCE")
    private Double balance;
}
