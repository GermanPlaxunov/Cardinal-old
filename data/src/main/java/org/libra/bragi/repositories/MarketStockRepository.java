package org.libra.bragi.repositories;

import org.libra.bragi.entities.MarketStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MarketStockRepository extends JpaRepository<MarketStockEntity, Long> {

    Optional<MarketStockEntity> findTopBySymbolAndDateGreaterThanOrderByDateAsc(String symbol, LocalDateTime lastStockDate);

    Optional<MarketStockEntity> findTopBySymbolOrderByDateAsc(String symbol);

}
