package org.project.data.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ACCOUNT")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "ACCOUNT_ID")
    private String accountId;
    @Column(name = "BALANCE")
    private Double balance;
    @Column(name = "OPEN_POSITION_CNT")
    private Integer openPositionCount;

}
