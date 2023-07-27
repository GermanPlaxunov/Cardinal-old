package org.project.data.repositories;

import org.project.data.entities.MarketStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MarketStockRepository extends JpaRepository<MarketStockEntity, Long> {

    Optional<MarketStockEntity> findTopBySymbolAndDateGreaterThanOrderByDateAsc(String symbol, LocalDateTime lastStockDate);

}
