package org.libra.indicators.indicators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.model.CoreStock;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class StandardDerivatives {

    private final SimpleMovingAverage simpleMovingAverage;

    public Double calculateStd(List<CoreStock> coreStocks, Long cacheDepth) {
        var symbol = coreStocks.get(0).getSymbol();
        log.debug("Start calculating STD for {}", symbol);
        var depth = coreStocks.size();
        var SMA = simpleMovingAverage.calculateSma(coreStocks, cacheDepth);
        var summ = 0.0;
        for (var i = 0; i < coreStocks.size(); i++) {
            summ += Math.pow((coreStocks.get(i).getClose() - SMA), 2);
        }
        var std = Math.sqrt(summ / depth);
        log.debug("STD for {} is {}", symbol, std);
        return std;
    }

}
