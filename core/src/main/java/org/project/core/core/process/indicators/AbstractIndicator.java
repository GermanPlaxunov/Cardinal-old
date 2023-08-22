package org.project.core.core.process.indicators;

import org.project.data.entities.CoreStockEntity;

import java.util.List;
import java.util.Objects;

public abstract class AbstractIndicator {

    protected List<Double> getPrices(List<CoreStockEntity> coreStockEntities) {
        return coreStockEntities.stream()
                .filter(Objects::nonNull)
                .map(CoreStockEntity::getClose)
                .toList();
    }

}
