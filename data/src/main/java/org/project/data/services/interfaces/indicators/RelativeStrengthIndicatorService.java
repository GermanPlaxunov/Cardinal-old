package org.project.data.services.interfaces.indicators;

import org.project.data.entities.indicators.RelativeStrengthIndicatorEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface RelativeStrengthIndicatorService extends AbstractIndicatorService<RelativeStrengthIndicatorEntity> {

    List<RelativeStrengthIndicatorEntity> findAllInPeriod(String symbol,
                                                          LocalDateTime from,
                                                          LocalDateTime to);

}
