package org.project.data.repositories.indicators;

import org.project.data.entities.indicators.StandardDerivativesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StandardDerivativesRepository extends JpaRepository<StandardDerivativesEntity, Long> {

    List<StandardDerivativesEntity> findAllBySymbol(String symbol);

    Optional<StandardDerivativesEntity> findTopBySymbolOrderByDateDesc(String symbol);

}
