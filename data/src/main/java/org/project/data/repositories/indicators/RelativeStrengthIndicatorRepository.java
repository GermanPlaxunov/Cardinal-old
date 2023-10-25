package org.project.data.repositories.indicators;

import org.project.data.entities.indicators.RelativeStrengthEntityDataItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RelativeStrengthIndicatorRepository extends JpaRepository<RelativeStrengthEntityDataItem, Long> {

    List<RelativeStrengthEntityDataItem> findAllBySymbol(String symbol);

    List<RelativeStrengthEntityDataItem> findAllBySymbolAndDateBetween(String symbol,
                                                                       LocalDateTime from,
                                                                       LocalDateTime to);

    Optional<RelativeStrengthEntityDataItem> findTopBySymbolOrderByDateDesc(String symbol);

    List<RelativeStrengthEntityDataItem> findAllBySymbolAndDateGreaterThanOrderByDateAsc(String symbol,
                                                                                         LocalDateTime date);

}
