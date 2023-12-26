package org.libra.data.repositories;

import org.libra.data.entities.CoreStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CoreStockRepository extends JpaRepository<CoreStockEntity, Long> {

    Optional<CoreStockEntity> findFirstBySymbolOrderByDateDesc(String symbol);

    Optional<CoreStockEntity> findFirstBySymbolAndDateLessThanOrderByDateDesc(String symbol,
                                                                              LocalDateTime date);

    boolean existsBySymbolAndDateLessThan(String symbol, LocalDateTime date);

    List<CoreStockEntity> findAllBySymbolAndDateGreaterThanOrderByDateAsc(String symbol,
                                                                          LocalDateTime date);

    Long countBySymbol(String symbol);
}
