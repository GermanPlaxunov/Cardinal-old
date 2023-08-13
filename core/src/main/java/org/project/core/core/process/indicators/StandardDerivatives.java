package org.project.core.core.process.indicators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.data.entities.CoreStockEntity;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class StandardDerivatives {

    private final SimpleMovingAverage simpleMovingAverage;

    public Double calculateStd(List<CoreStockEntity> coreStockEntities) {
        var symbol = coreStockEntities.get(0).getSymbol();
        log.info("Start calculating STD for {}", symbol);
        var depth = coreStockEntities.size();
        var SMA = simpleMovingAverage.calculateSma(coreStockEntities);
        var summ = 0.0;
        for (var i = 0; i < coreStockEntities.size(); i++) {
            summ += Math.pow((coreStockEntities.get(i).getClose() - SMA), 2);
        }
        var std = Math.sqrt(summ / depth);
        log.info("STD for {} is {}", symbol, std);
        return std;
    }

}
