package org.cardinal.data.repositories.history;

import org.cardinal.data.entities.history.CandleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CandleRepository extends JpaRepository<CandleEntity, Long> {

    List<CandleEntity> findAllByFigi(String instrumentId);

    Optional<CandleEntity> findTopByFigiOrderByDateTimeDesc(String figi);

    boolean existsByFigi(String figi);

}
