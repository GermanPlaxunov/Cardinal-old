package org.libra.indicators.indicators;

import lombok.extern.slf4j.Slf4j;
import org.project.model.CoreStock;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
public abstract class AbstractIndicator {

    /**
     * Returns stocks with required depth.
     *
     * @param coreStocks - stocks obtained using maximum
     *                   cacheDepth ordered by date asc
     * @param cacheDepth - cache depth
     * @return stocks with required depth
     */
    protected List<CoreStock> getCachedStocks(List<CoreStock> coreStocks, Long cacheDepth) {
        var earliestDate = getEarliestDate(coreStocks, cacheDepth);
        return coreStocks.stream()
                .filter(Objects::nonNull)
                .filter(stock -> stock.getDate().isAfter(earliestDate))
                .toList();
    }

    protected List<Double> getPrices(List<CoreStock> coreStocks) {
        return coreStocks.stream()
                .filter(Objects::nonNull)
                .map(CoreStock::getClose)
                .toList();
    }

    protected Double standardDeviation(List<Double> inputArray) {
        var sum = 0.0;
        for (var num : inputArray) {
            sum += num;
        }
        var mean = sum / inputArray.size();
        var standardDeviation = 0.0;
        for (var num : inputArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }
        return Math.sqrt(standardDeviation / inputArray.size());
    }

    protected Double getSum(List<Double> elements) {
        return elements.stream()
                .reduce(0.0, Double::sum);
    }

    private LocalDateTime getEarliestDate(List<CoreStock> stocks, Long cacheDepthInSeconds) {
        var latestDate = stocks.stream()
                .map(CoreStock::getDate)
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
