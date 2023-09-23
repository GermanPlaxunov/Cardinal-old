package org.project.data.repositories.indicators;

import org.project.data.entities.indicators.ExponentialMovingAverageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExponentialMovingAverageRepository extends JpaRepository<ExponentialMovingAverageEntity, Long> {

    List<ExponentialMovingAverageEntity> findAllBySymbol(String symbol);

    Optional<ExponentialMovingAverageEntity> findTopBySymbolOrderByDateDesc(String symbol);

}
