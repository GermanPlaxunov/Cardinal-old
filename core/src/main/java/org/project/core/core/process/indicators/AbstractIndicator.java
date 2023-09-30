package org.project.core.core.process.indicators;

import org.project.data.entities.CoreStockEntity;

import java.util.List;
import java.util.Objects;

public abstract class AbstractIndicator {

    /**
     * Returns stocks with required depth.
     *
     * @param entities - stocks obtained using maximum cacheDepth
     * @param cacheDepth - cache depth
     * @return stocks with required depth
     */
    protected List<CoreStockEntity> getCachedStocks(List<CoreStockEntity> entities, Long cacheDepth) {
        return entities.stream()
                .filter(Objects::nonNull)
                .skip(entities.size() - cacheDepth)
                .toList();
    }

    protected List<Double> getPrices(List<CoreStockEntity> entities) {
        return entities.stream()
                .filter(Objects::nonNull)
                .map(CoreStockEntity::getClose)
                .toList();
    }

}
