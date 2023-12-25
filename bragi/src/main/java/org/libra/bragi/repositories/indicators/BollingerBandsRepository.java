package org.libra.bragi.repositories.indicators;

import org.libra.bragi.entities.indicators.BollingerBandsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BollingerBandsRepository extends JpaRepository<BollingerBandsEntity, Long> {

    List<BollingerBandsEntity> findAllBySymbol(String symbol);

    Optional<BollingerBandsEntity> findTopBySymbolOrderByDateDesc(String symbol);

    List<BollingerBandsEntity> findAllBySymbolAndDateGreaterThanOrderByDateAsc(String symbol,
                                                                               LocalDateTime date);

}
