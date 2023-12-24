package org.project.data.repositories.indicators;

import org.project.data.entities.indicators.SimpleMovingAverageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SimpleMovingAverageRepository extends JpaRepository<SimpleMovingAverageEntity, Long> {

    List<SimpleMovingAverageEntity> findAllBySymbol(String symbol);

    Optional<SimpleMovingAverageEntity> findTopBySymbolOrderByDateDesc(String symbol);

    List<SimpleMovingAverageEntity> findAllBySymbolAndDateGreaterThanOrderByDateAsc(String symbol,
                                                                                    LocalDateTime date);
}
