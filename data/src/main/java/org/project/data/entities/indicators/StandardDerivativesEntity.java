package org.project.data.entities.indicators;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "STD_DERIVS")
public class StandardDerivativesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "SYMBOl")
    private String symbol;
    @Column(name = "DATE")
    private LocalDateTime date;
    @Column(name = "VALUE")
    private Double value;
    @Column(name = "DEPTH")
    private Long depth;

}
