package org.project.data.repositories.indicators;

import org.project.data.entities.indicators.RelativeStrengthIndicatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelativeStrengthIndicatorRepository extends JpaRepository<RelativeStrengthIndicatorEntity, Long> {

    List<RelativeStrengthIndicatorEntity> findAllBySymbol(String symbol);

}
