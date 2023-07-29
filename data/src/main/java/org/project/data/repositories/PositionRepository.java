package org.project.data.repositories;

import org.project.data.entities.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PositionRepository extends JpaRepository<PositionEntity, Long> {

    List<PositionEntity> findBySymbolAndAccountIdAndIsOpen(String symbol, String accountId, Boolean isOpen);

    Optional<PositionEntity> findFirstBySymbolAndCloseDateIsNotNullOrderByCloseDateDesc(String symbol);

}
