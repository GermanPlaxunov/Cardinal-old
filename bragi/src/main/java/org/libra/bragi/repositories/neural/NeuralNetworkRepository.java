package org.libra.bragi.repositories.neural;

import org.libra.bragi.entities.neural.NeuralNetworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NeuralNetworkRepository extends JpaRepository<NeuralNetworkEntity, Long> {

    List<NeuralNetworkEntity> findAllBySymbol(String symbol);

    Optional<NeuralNetworkEntity> findAllByName(String name);
}
