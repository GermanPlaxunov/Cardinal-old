package org.project.core.core.process.indicators;

import lombok.extern.slf4j.Slf4j;
import org.project.data.entities.CoreStockEntity;

import java.util.List;

@Slf4j
public class SimpleMovingAverage extends AbstractIndicator {

    /**
     * Simple average price over a period of time.
     *
     * @param coreStockEntities - list of all points ordered by date
     * @return sma
     */
    public Double calculateSma(List<CoreStockEntity> coreStockEntities) {
        var symbol = coreStockEntities.get(0).getSymbol();
        log.debug("Start calculating SMA for {}", symbol);
        var depth = coreStockEntities.size();
        var prices = getPrices(coreStockEntities);
        var summ = prices.stream()
                .reduce(0.0, Double::sum);
        var sma = summ / depth;
        log.debug("SMA for {} is {}", symbol, sma);
        return sma;
    }

}
