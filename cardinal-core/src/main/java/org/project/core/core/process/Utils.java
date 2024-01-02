package org.project.core.core.process;

import org.cardinal.data.entities.CoreStockEntity;
import org.project.model.CoreStock;

import java.util.List;

public class Utils {

    public static Double getMinClose(List<CoreStock> stocks) {
        return stocks.stream()
                .map(CoreStock::getClose)
                .min(Double::compareTo)
                .orElse(null);
    }

    public static Double getMaxClose(List<CoreStock> stocks) {
        return stocks.stream()
                .map(CoreStock::getClose)
                .max(Double::compareTo)
                .orElse(null);
    }

    /**
     * Trims the list of stocks to cache depth in seconds.
     * Stocks should be sorted in ascending order by date.
     *
     * @param stocks            - list of stocks in asc order by date.
     * @param cacheDepthSeconds - the depth in the seconds.
     * @return right trimmed list.
     */
    public static List<CoreStockEntity> trimStocksToCacheDepth(List<CoreStockEntity> stocks, Long cacheDepthSeconds) {
        var earliestDate = stocks.get(stocks.size() - 1)
                .getDate()
                .minusSeconds(cacheDepthSeconds);
        return stocks.stream()
                .filter(stock -> stock.getDate().isAfter(earliestDate) ||
                        stock.getDate().equals(earliestDate))
                .toList();
    }

}
