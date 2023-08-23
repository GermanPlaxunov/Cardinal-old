package org.project.data.repositories.indicators;

import org.project.data.entities.indicators.ExponentialMovingAverageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExponentialMovingAverageRepository extends JpaRepository<ExponentialMovingAverageEntity, Long> {

    List<ExponentialMovingAverageEntity> findAllBySymbol(String symbol);

}
