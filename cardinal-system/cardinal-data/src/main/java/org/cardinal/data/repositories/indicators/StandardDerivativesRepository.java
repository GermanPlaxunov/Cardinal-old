package org.cardinal.data.repositories.indicators;

import org.cardinal.data.entities.indicators.StandardDerivativesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface StandardDerivativesRepository extends JpaRepository<StandardDerivativesEntity, Long> {

    List<StandardDerivativesEntity> findAllBySymbol(String symbol);

    Optional<StandardDerivativesEntity> findTopBySymbolOrderByDateDesc(String symbol);

    List<StandardDerivativesEntity> findAllBySymbolAndDateGreaterThanOrderByDateAsc(String symbol,
                                                                                    LocalDateTime date);

}
