package org.cardinal.data.repositories;

import org.cardinal.data.entities.LastProvidedStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface LastProvidedStockRepository extends JpaRepository<LastProvidedStockEntity, Long> {

    Optional<LastProvidedStockEntity> findBySymbol(String symbol);

    @Modifying
    @Query("update LastProvidedStockEntity last set last.stockId = :stockId, last.stockDate = :stockDate " +
            "where last.symbol = :symbol")
    void update(@Param("stockId") Long stockId,
                @Param("stockDate") LocalDateTime stockDate,
                @Param("symbol") String symbol);

    boolean existsBySymbol(String symbol);

}
