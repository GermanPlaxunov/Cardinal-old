package org.project.core.core.process.indicators;

import lombok.extern.slf4j.Slf4j;
import org.project.core.core.MathUtils;
import org.project.model.CoreStock;
import org.project.model.indicators.Rsi;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RelativeStrengthIndicator extends AbstractIndicator {

    /**
     * Relative strength indicator based on price changes over period
     * to capture strength/magnitude of price moves. It comprises a lookback
     * period, which it uses to compute the magnitude of the average of gains/price increases
     * over that period, as well as the magnitude of the averages of losses/price decreases
     * over that period. Then, it computes the RSI value that normalizes the signal value
     * to stay between 0 and 100, and attempts to capture if there have been many more gains
     * relative to the losses, or if there have been many more losses relative to the gains.
     * RSI values over 50% indicate an uptrend, while RSI values below 50% indicate a downtrend.
     *
     * @param coreStocks - list of all points.
     * @return rsi
     */
    public Rsi calculateRsi(List<CoreStock> coreStocks, Long cacheDepth) {
        var symbol = coreStocks.get(0).getSymbol();
        log.info("Start calculating RSI for {}", symbol);
        var stocks = getCachedStocks(coreStocks, cacheDepth);
        var prices = getPrices(stocks);
        var rsi = getTotalGainAndLosses(prices);
        var relativeStrength = getRelativeStrength(rsi);
        rsi.setRsi(calculateRsi(relativeStrength));
        log.info("RSI for {} is {}", symbol, rsi.getRsi());
        return rsi;
    }

    private Double calculateRsi(Double relativeStrength) {
        return 100 - (100 / (1 + relativeStrength));
    }

    private Double getRelativeStrength(Rsi rsi) {
        var gainSum = MathUtils.getSum(rsi.getGainList());
        var lossSum = MathUtils.getSum(rsi.getLossList());
        rsi.setGainSumm(gainSum)
                .setLossSumm(lossSum);
        var num = rsi.getNumberOfPeriods();
        return (gainSum / num) / (lossSum / num);
    }

    private Rsi getTotalGainAndLosses(List<Double> prices) {
        var gains = new ArrayList<Double>();
        var losses = new ArrayList<Double>();
        for (var i = 1; i < prices.size(); i++) {
            gains.add(absoluteGainOverPeriod(prices.get(i), prices.get(i - 1)));
            losses.add(absoluteLossOverPeriod(prices.get(i), prices.get(i - 1)));
        }
        return new Rsi()
                .setNumberOfPeriods(prices.size())
                .setGainList(gains)
                .setLossList(losses);
    }

    private Double absoluteGainOverPeriod(Double currClosePrice, Double previousPrice) {
        return Math.max(0.0, currClosePrice - previousPrice);
    }

    private Double absoluteLossOverPeriod(Double currClosePrice, Double previousPrice) {
        return Math.max(0.0, previousPrice - currClosePrice);
    }
}
