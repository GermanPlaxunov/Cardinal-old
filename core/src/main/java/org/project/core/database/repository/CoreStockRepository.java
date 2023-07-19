package org.project.core.database.repository;

import org.project.core.database.entity.CoreStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CoreStockRepository extends JpaRepository<CoreStockEntity, Long> {

    Optional<CoreStockEntity> findFirstBySymbolOrderByDateDesc(String symbol);

    Optional<CoreStockEntity> findFirstBySymbolAndDateLessThanOrderByDateDesc(String symbol,
                                                                              LocalDateTime date);

}
