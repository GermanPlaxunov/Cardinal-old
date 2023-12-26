package org.libra.data.repositories.indicators;

import org.libra.data.entities.indicators.ExponentialMovingAverageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ExponentialMovingAverageRepository extends JpaRepository<ExponentialMovingAverageEntity, Long> {

    List<ExponentialMovingAverageEntity> findAllBySymbol(String symbol);

    Optional<ExponentialMovingAverageEntity> findTopBySymbolOrderByDateDesc(String symbol);

    List<ExponentialMovingAverageEntity> findAllBySymbolAndDateGreaterThanOrderByDateAsc(String symbol,
                                                                                         LocalDateTime date);

}
