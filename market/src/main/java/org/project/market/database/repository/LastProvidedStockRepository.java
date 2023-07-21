package org.project.market.database.repository;

import org.project.market.database.entity.LastProvidedStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LastProvidedStockRepository extends JpaRepository<LastProvidedStockEntity, Long> {

    Optional<LastProvidedStockEntity> findBySymbol(String symbol);

}
