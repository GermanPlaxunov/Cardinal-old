package org.libra.data.entities;

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
    @Column(name = "JOB_NAME")
    private String jobName;
    @Column(name = "INTERVAL_MILLIS")
    private Long intervalMillis;
    @Column(name = "STOCK")
    private String stockName;
    @Column(name = "JOB_TYPE")
    private String jobType;
    @Column(name = "JOB_DESCRIPTION")
    private String jobDescription;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "TRIGGER_NAME")
    private String triggerName;
    @Column(name = "TRIGGER_DESCRIPTION")
    private String triggerDescription;

}
