package org.project.data.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PROCESS_PARAMS",
        indexes = @Index(name = "PARAM_NAME_IDX", columnList = "NAME"))
public class ProcessParamsEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "STRING_VALUE")
    private String stringValue;
    @Column(name = "NUMBER_VALUE")
    private Double numberValue;
    @Column(name = "DATE_VALUE")
    private LocalDateTime dateValue;
}
