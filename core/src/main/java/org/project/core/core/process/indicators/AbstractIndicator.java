package org.project.core.core.process.indicators;

import lombok.extern.slf4j.Slf4j;
import org.project.data.entities.CoreStockEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
public abstract class AbstractIndicator {

    /**
     * Returns stocks with required depth.
     *
     * @param entities   - stocks obtained using maximum
     *                   cacheDepth ordered by date asc
     * @param cacheDepth - cache depth
     * @return stocks with required depth
     */
    protected List<CoreStockEntity> getCachedStocks(List<CoreStockEntity> entities, Long cacheDepth) {
        var earliestDate = getEarliestDate(entities, cacheDepth);
        return entities.stream()
                .filter(Objects::nonNull)
                .filter(stock -> stock.getDate().isAfter(earliestDate))
                .toList();
    }

    protected List<Double> getPrices(List<CoreStockEntity> entities) {
        return entities.stream()
                .filter(Objects::nonNull)
                .map(CoreStockEntity::getClose)
                .toList();
    }

    private LocalDateTime getEarliestDate(List<CoreStockEntity> stocks, Long cacheDepthInSeconds) {
        var latestDate = stocks.stream()
                .map(CoreStockEntity::getDate)
                .max(LocalDateTime::compareTo)
                .orElse(null);
        if (latestDate != null) {
            return latestDate.minusSeconds(cacheDepthInSeconds);
        } else {
            log.error("Error while searching for latest date in a list.");
        }
        return null;
    }

}
