package org.project.core.core.process.indicators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.core.core.process.vars.ProcessVars;
import org.project.data.entities.CoreStockEntity;
import org.project.data.services.interfaces.CoreStockService;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class IndicatorsCollector {

    private final IndicatorsSaver indicatorsSaver;
    private final AbsolutePriceOscillator absolutePriceOscillator;
    private final ExponentialMovingAverage exponentialMovingAverage;
    private final RelativeStrengthIndicator relativeStrengthIndicator;
    private final SimpleMovingAverage simpleMovingAverage;
    private final StandardDerivatives standardDerivatives;
    private final BollingerBands bollingerBands;
    private final CoreStockService coreStockService;

    public ProcessVars collect(String symbol, Long cacheSeconds) {
        ProcessVars processVars = null;
        var stocks = coreStockService.findCache(symbol, cacheSeconds);
        if(stocks.size() > 5) {
            log.info("Amount of stocks to collect indexes: {}", stocks.size());
            var apo = absolutePriceOscillator.calculateApo(stocks);
            var ema = exponentialMovingAverage.calculateEma(stocks);
            var rsi = relativeStrengthIndicator.calculateRsi(stocks);
            var sma = simpleMovingAverage.calculateSma(stocks);
            var std = standardDerivatives.calculateStd(stocks);
            var bband = bollingerBands.calculateBband(stocks);
            processVars = new ProcessVars()
                    .setSymbol(symbol)
                    .setDate(getIndicatorDate(stocks))
                    .setApo(apo)
                    .setEma(ema)
                    .setRsi(rsi)
                    .setSma(sma)
                    .setStd(std)
                    .setBband(bband)
                    .setDepth(cacheSeconds);
            indicatorsSaver.saveAllIndicators(processVars);
        }
        return processVars;
    }

    private LocalDateTime getIndicatorDate(List<CoreStockEntity> stocks) {
        var size = stocks.size();
        return stocks.stream()
                .skip(size - 1)
                .map(CoreStockEntity::getDate)
                .findFirst()
                .orElse(null);
    }

}
