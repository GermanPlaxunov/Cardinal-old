package org.libra.data.services.interfaces.indicators;

import org.libra.data.entities.indicators.RelativeStrengthEntityDataItem;

import java.time.LocalDateTime;
import java.util.List;

public interface RelativeStrengthIndicatorService extends AbstractIndicatorService<RelativeStrengthEntityDataItem> {

    List<RelativeStrengthEntityDataItem> findAllInPeriod(String symbol,
                                                         LocalDateTime from,
                                                         LocalDateTime to);
    RelativeStrengthEntityDataItem findLast(String symbol);

}
