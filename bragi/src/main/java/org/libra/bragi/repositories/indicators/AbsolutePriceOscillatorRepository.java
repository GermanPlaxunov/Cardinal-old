package org.libra.bragi.repositories.indicators;

import org.libra.bragi.entities.indicators.AbsolutePriceOscillatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AbsolutePriceOscillatorRepository extends JpaRepository<AbsolutePriceOscillatorEntity, Long> {

    List<AbsolutePriceOscillatorEntity> findAllBySymbol(String symbol);

    Optional<AbsolutePriceOscillatorEntity> findTopBySymbolOrderByDateDesc(String symbol);

    List<AbsolutePriceOscillatorEntity> findAllBySymbolAndDateGreaterThanOrderByDateAsc(String symbol,
                                                                                        LocalDateTime date);

}
