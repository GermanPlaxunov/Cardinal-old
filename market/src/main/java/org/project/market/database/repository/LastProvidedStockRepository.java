package org.project.market.database.repository;

import org.project.market.database.entity.LastProvidedStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LastProvidedStockRepository extends JpaRepository<LastProvidedStockEntity, Long> {

    Optional<LastProvidedStockEntity> findBySymbol(String symbol);

    @Modifying
    @Query("update LastProvidedStockEntity last set last.stockId = :stockId " +
            "where last.symbol = :symbol")
    void update(@Param("stockId") Long stockId,
                @Param("symbol") String symbol);

}
