package org.project.data.repositories;

import org.project.data.entities.NeuralPredictionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface NeuralPredictionRepository extends JpaRepository<NeuralPredictionEntity, Long> {

    List<NeuralPredictionEntity> findAllBySymbolAndIndicatorNameAndDateGreaterThanOrderByDateAsc(String symbol,
                                                                                                 String indicatorName,
                                                                                                 LocalDateTime date);

    Optional<NeuralPredictionEntity> findFirstBySymbolAndIndicatorNameOrderByDateDesc(String symbol,
                                                                                      String indicatorName);

}
