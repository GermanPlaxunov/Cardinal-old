package org.project.core.database.repository;

import org.project.core.database.entity.MarketDealEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MarketDealRepository extends JpaRepository<MarketDealEntity, Long> {

    List<MarketDealEntity> findAllBySymbolsAndCloseDateIsNull(String symbols);

}
