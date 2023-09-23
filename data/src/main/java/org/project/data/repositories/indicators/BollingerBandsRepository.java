package org.project.data.repositories.indicators;

import org.project.data.entities.indicators.BollingerBandsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BollingerBandsRepository extends JpaRepository<BollingerBandsEntity, Long> {

    List<BollingerBandsEntity> findAllBySymbol(String symbol);

    Optional<BollingerBandsEntity> findTopBySymbolOrderByDateDesc(String symbol);

}
