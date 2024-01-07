package org.cardinal.data.repositories;

import org.cardinal.data.entities.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PositionRepository extends JpaRepository<PositionEntity, Long> {

    List<PositionEntity> findByFigiAndAccountIdAndIsOpen(String symbol, String accountId, Boolean isOpen);

    Optional<PositionEntity> findFirstByFigiAndCloseDateIsNotNullOrderByCloseDateDesc(String symbol);

    Integer countByFigiAndIsOpen(String symbol, boolean isOpen);
}
