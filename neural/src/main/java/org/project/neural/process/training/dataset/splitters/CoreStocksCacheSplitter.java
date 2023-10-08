package org.project.neural.process.training.dataset.splitters;

import org.project.data.entities.CoreStockEntity;

import java.util.List;

public class CoreStocksCacheSplitter {

    /**
     * Separate stocks which are out of cache depth.
     * Stocks should be sorted by date in asc mode.
     *
     * @param stocks            - entire list of stocks.
     * @param cacheDepthSeconds - cache depth in seconds.
     * @return stocks which are in cache depth period.
     */
    public List<CoreStockEntity> splitCacheDepth(List<CoreStockEntity> stocks, Long cacheDepthSeconds) {
        var edgeDate = stocks.get(stocks.size() - 1)
                .getDate()
                .minusSeconds(cacheDepthSeconds);
        return stocks.stream()
                .filter(stock -> stock.getDate().isAfter(edgeDate))
                .toList();
    }

}
