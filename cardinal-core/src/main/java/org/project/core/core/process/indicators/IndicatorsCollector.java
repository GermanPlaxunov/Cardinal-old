package org.project.core.core.process.indicators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cardinal.data.cache.CacheDepthProvider;
import org.cardinal.indicators.indicators.*;
import org.project.model.CoreStock;
import org.project.model.ProcessVars;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class IndicatorsCollector {

    private final RelativeStrengthIndicator relativeStrengthIndicator;
    private final ExponentialMovingAverage exponentialMovingAverage;
    private final AbsolutePriceOscillator absolutePriceOscillator;
    private final SimpleMovingAverage simpleMovingAverage;
    private final StandardDerivatives standardDerivatives;
    private final CacheDepthProvider cacheDepthProvider;
    private final IndicatorsSaver indicatorsSaver;
    private final BollingerBands bollingerBands;

    public void collect(String symbol, ProcessVars<CoreStock> processVars) {
        var stocks = processVars.getStocks();
        var depths = cacheDepthProvider.getAllIndicatorsCacheDepths(symbol);
        if (stocks.size() > 10) { //TODO: To params
            log.info("Amount of stocks to collect indexes: {}", stocks.size());
            var apo = absolutePriceOscillator.calculateApo(stocks, depths.getApoDepth());
            var ema = exponentialMovingAverage.calculateEma(stocks, depths.getEmaDepth());
            var rsi = relativeStrengthIndicator.calculateRsi(stocks, depths.getRsiDepth());
            var sma = simpleMovingAverage.calculateSma(stocks, depths.getSmaDepth());
            var std = standardDerivatives.calculateStd(stocks, depths.getStdDepth());
            var bband = bollingerBands.calculateBband(stocks, depths.getBbandDepth());
            processVars = new ProcessVars()
                    .setSymbol(symbol)
                    .setDate(getIndicatorDate(stocks))
                    .setApo(apo)
                    .setEma(ema)
                    .setRsi(rsi)
                    .setSma(sma)
                    .setStd(std)
                    .setBband(bband)
                    .setDepth(depths.getMaxDepth());
            indicatorsSaver.saveAllIndicators(processVars);
        }
    }

    private LocalDateTime getIndicatorDate(List<CoreStock> stocks) {
        var size = stocks.size();
        return stocks.stream()
                .skip(size - 1)
                .map(CoreStock::getDate)
                .findFirst()
                .orElse(null);
    }

}
