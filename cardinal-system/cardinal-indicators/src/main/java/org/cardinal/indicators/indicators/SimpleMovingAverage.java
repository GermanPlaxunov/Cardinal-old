package org.cardinal.indicators.indicators;

import lombok.extern.slf4j.Slf4j;
import org.project.model.CoreStock;

import java.util.List;

@Slf4j
public class SimpleMovingAverage extends AbstractIndicator {

    /**
     * Simple average price over a period of time.
     *
     * @param coreStocks - list of all points ordered by date
     * @return sma
     */
    public Double calculateSma(List<CoreStock> coreStocks, Long cacheDepth) {
        var symbol = coreStocks.get(0).getSymbol();
        log.debug("Start calculating SMA for {}", symbol);
        var depth = coreStocks.size();
        var stocks = getCachedStocks(coreStocks, cacheDepth);
        var prices = getPrices(stocks);
        var summ = prices.stream()
                .reduce(0.0, Double::sum);
        var sma = summ / depth;
        log.debug("SMA for {} is {}", symbol, sma);
        return sma;
    }

}
