package org.project.data.repositories.indicators;

import org.project.data.entities.indicators.AbsolutePriceOscillatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbsolutePriceOscillatorRepository extends JpaRepository<AbsolutePriceOscillatorEntity, Long> {

    List<AbsolutePriceOscillatorEntity> findAllBySymbol(String symbol);

}
