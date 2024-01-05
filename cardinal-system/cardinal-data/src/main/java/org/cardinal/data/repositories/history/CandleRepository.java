package org.cardinal.data.repositories.history;

import org.cardinal.data.entities.history.CandleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandleRepository extends JpaRepository<CandleEntity, Long> {

    List<CandleEntity> findAllByInstrumentId(String instrumentId);

}
