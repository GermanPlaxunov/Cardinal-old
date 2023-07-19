package org.project.core.database.repository;

import org.project.core.database.entity.PriceDiffSignalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceDiffSignalRepository extends JpaRepository<PriceDiffSignalEntity, Long> {

    Long countBySymbol(String symbol);

    Optional<PriceDiffSignalEntity> findFirstBySymbolAndDateLessThanOrderByDateDesc(String symbol, LocalDateTime date);

}
