package org.project.core.core.process;

import lombok.RequiredArgsConstructor;
import org.project.core.core.process.indicators.*;
import org.project.core.core.process.vars.ProcessVars;
import org.project.data.services.interfaces.CoreStockService;

@RequiredArgsConstructor
public class IndicatorsCollector {

    private final AbsolutePriceOscillator absolutePriceOscillator;
    private final ExponentialMovingAverage exponentialMovingAverage;
    private final RelativeStrengthIndicator relativeStrengthIndicator;
    private final SimpleMovingAverage simpleMovingAverage;
    private final StandardDerivatives standardDerivatives;
    private final BollingerBands bollingerBands;
    private final CoreStockService coreStockService;

    public ProcessVars collect(String symbol, Long cacheSeconds) {
        var stocks = coreStockService.findCache(symbol, cacheSeconds);
        var apo = absolutePriceOscillator.calculateApo(stocks);
        var ema = exponentialMovingAverage.calculateEma(stocks);
        var rsi = relativeStrengthIndicator.calculateRsi(stocks);
        var sma = simpleMovingAverage.calculateSma(stocks);
        var std = standardDerivatives.calculateStd(stocks);
        var bband = bollingerBands.calculateBband(stocks);
        return new ProcessVars()
                .setApo(apo)
                .setEma(ema)
                .setRsi(rsi)
                .setSma(sma)
                .setStd(std)
                .setBband(bband);
    }

}
