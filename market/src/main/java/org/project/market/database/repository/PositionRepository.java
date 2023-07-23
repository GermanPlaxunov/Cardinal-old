package org.project.market.database.repository;

import org.project.market.database.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<PositionEntity, Long> {

    Optional<PositionEntity> findBySymbolAndAccountIdAndIsOpen(String symbol, String accountId, Boolean isOpen);

}
