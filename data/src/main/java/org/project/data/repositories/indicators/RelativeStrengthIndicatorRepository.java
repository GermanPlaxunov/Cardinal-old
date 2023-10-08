package org.project.data.repositories.indicators;

import org.project.data.entities.indicators.RelativeStrengthIndicatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RelativeStrengthIndicatorRepository extends JpaRepository<RelativeStrengthIndicatorEntity, Long> {

    List<RelativeStrengthIndicatorEntity> findAllBySymbol(String symbol);

    List<RelativeStrengthIndicatorEntity> findAllBySymbolAndDateBetween(String symbol,
                                                                        LocalDateTime from,
                                                                        LocalDateTime to);

    Optional<RelativeStrengthIndicatorEntity> findTopBySymbolOrderByDateDesc(String symbol);

    List<RelativeStrengthIndicatorEntity> findAllBySymbolAndDateGreaterThanOrderByDateAsc(String symbol,
                                                                                          LocalDateTime date);

}
