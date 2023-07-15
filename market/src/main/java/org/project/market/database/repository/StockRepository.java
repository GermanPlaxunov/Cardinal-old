package org.project.market.database.repository;

import org.project.market.database.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface StockRepository extends JpaRepository<StockEntity, Long> {

    Optional<StockEntity> findFirstBySymbolAndDateGreaterThanOrderByDateAsc(String symbol, LocalDateTime prev);

}
