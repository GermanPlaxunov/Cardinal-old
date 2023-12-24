package org.project.data.repositories;

import org.project.data.entities.PriceDiffSignalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceDiffSignalRepository extends JpaRepository<PriceDiffSignalEntity, Long> {

    Long countBySymbol(String symbol);

    Optional<PriceDiffSignalEntity> findTopBySymbolOrderByDateDesc(String symbol);
}
