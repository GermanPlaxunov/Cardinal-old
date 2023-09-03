package org.project.data.entities.neural;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "NEURAL_NETWORK")
public class NeuralNetworkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SYMBOL")
    private String symbol;
    @Column(name = "START_DATASET")
    private LocalDateTime startDataset;
    @Column(name = "END_DATASET")
    private LocalDateTime endDataset;
    @Column(name = "VECTOR")
    private String vector;

}
