package org.libra.data.repositories.neural;

import org.libra.data.entities.neural.NeuralNetworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NeuralNetworkRepository extends JpaRepository<NeuralNetworkEntity, Long> {

    List<NeuralNetworkEntity> findAllBySymbol(String symbol);

    Optional<NeuralNetworkEntity> findAllByName(String name);
}
