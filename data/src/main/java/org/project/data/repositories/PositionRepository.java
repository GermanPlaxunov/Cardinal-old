package org.project.data.repositories;

import org.project.data.entities.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<PositionEntity, Long> {

    Optional<PositionEntity> findBySymbolAndAccountIdAndIsOpen(String symbol, String accountId, Boolean isOpen);

}
