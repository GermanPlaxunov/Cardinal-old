package org.project.data.repositories.indicators;

import org.project.data.entities.indicators.SimpleMovingAverageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SimpleMovingAverageRepository extends JpaRepository<SimpleMovingAverageEntity, Long> {

    List<SimpleMovingAverageEntity> findAllBySymbol(String symbol);

}
