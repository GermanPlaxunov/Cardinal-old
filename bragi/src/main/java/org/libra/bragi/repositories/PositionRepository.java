package org.libra.bragi.repositories;

import org.libra.bragi.entities.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PositionRepository extends JpaRepository<PositionEntity, Long> {

    List<PositionEntity> findBySymbolAndAccountIdAndIsOpen(String symbol, String accountId, Boolean isOpen);

    Optional<PositionEntity> findFirstBySymbolAndCloseDateIsNotNullOrderByCloseDateDesc(String symbol);

    Integer countBySymbolAndIsOpen(String symbol, boolean isOpen);
}
