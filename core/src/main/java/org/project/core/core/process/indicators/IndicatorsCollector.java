package org.project.core.core.process.indicators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.core.core.process.params.cache.CacheDepthProvider;
import org.project.core.core.process.vars.ProcessVars;
import org.project.data.entities.CoreStockEntity;
import org.project.data.services.interfaces.CoreStockService;

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
    private final CoreStockService coreStockService;
    private final IndicatorsSaver indicatorsSaver;
    private final BollingerBands bollingerBands;

    public ProcessVars collect(String symbol) {
        ProcessVars processVars = null;
        var depths = cacheDepthProvider.getAllIndicatorsCacheDepths(symbol);
        var stocks = coreStockService.findCache(symbol, depths.getMaxDepth());
        if (stocks.size() > 10) {
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
