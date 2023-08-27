package org.project.data.repositories.neural;

import org.project.data.entities.neural.NeuralNetworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NeuralNetworkRepository extends JpaRepository<NeuralNetworkEntity, Long> {

    List<NeuralNetworkEntity> findAllBySymbol(String symbol);

}
