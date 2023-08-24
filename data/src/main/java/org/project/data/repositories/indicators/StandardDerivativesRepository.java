package org.project.data.repositories.indicators;

import org.project.data.entities.indicators.StandardDerivativesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StandardDerivativesRepository extends JpaRepository<StandardDerivativesEntity, Long> {

    List<StandardDerivativesEntity> findAllBySymbol(String symbol);

}
