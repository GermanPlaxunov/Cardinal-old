package org.libra.bragi.services.interfaces.indicators;

import org.libra.bragi.entities.indicators.RelativeStrengthEntityDataItem;

import java.time.LocalDateTime;
import java.util.List;

public interface RelativeStrengthIndicatorService extends AbstractIndicatorService<RelativeStrengthEntityDataItem> {

    List<RelativeStrengthEntityDataItem> findAllInPeriod(String symbol,
                                                         LocalDateTime from,
                                                         LocalDateTime to);
    RelativeStrengthEntityDataItem findLast(String symbol);

}
