package org.project.data.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "JOB")
public class JobEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "INTERVAL_MILLIS")
    private Long millis;
    @Column(name = "STOCK")
    private String stockName;

}
