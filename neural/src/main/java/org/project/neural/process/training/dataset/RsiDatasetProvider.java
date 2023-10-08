package org.project.neural.process.training.dataset;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.CoreStockEntity;
import org.project.data.entities.indicators.ExponentialMovingAverageEntity;
import org.project.data.entities.indicators.RelativeStrengthIndicatorEntity;
import org.project.data.services.interfaces.ProcessParamsService;
import org.project.data.services.interfaces.indicators.RelativeStrengthIndicatorService;
import org.project.model.Indicators;
import org.project.neural.process.training.dataset.delta.PriceChangeCalculator;
import org.project.neural.process.training.dataset.splitters.CoreStocksSplitter;
import org.project.neural.process.training.dataset.splitters.IndicatorSplitter;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class RsiDatasetProvider implements DatasetProvider {

    private final IndicatorSplitter<RelativeStrengthIndicatorEntity> indicatorSplitter;
    private final RelativeStrengthIndicatorService relativeStrengthIndicatorService;
    private final PriceChangeCalculator priceChangeCalculator;
    private final ProcessParamsService processParamsService;
    private final CoreStocksSplitter coreStocksSplitter;

    /**
     * Should provide a list of lists. Each list for RSI contains:
     * priceChange - the price delta comparing previous datapoint;
     * gl - gain to loss attitude for each period;
     *
     * @param symbol - the name of the stock.
     * @param stocks - the list of stocks.
     * @return dataset.
     */
    @Override
    public List<List<Double>> getData(String symbol, List<CoreStockEntity> stocks) {
        var cacheDepthSeconds = processParamsService.getTrainInterval(symbol, Indicators.RSI);
        var intervalSeconds = processParamsService.getTrainInterval(symbol, Indicators.RSI);
        var allIndicators = relativeStrengthIndicatorService.findCache(symbol, cacheDepthSeconds);
        var indicators = indicatorSplitter.split(allIndicators, intervalSeconds);
        var filteredStocks = coreStocksSplitter.split(stocks, intervalSeconds);
        var priceChanges = priceChangeCalculator.getPriceChanges(filteredStocks);
        return map(indicators, priceChanges);
    }

    /**
     * Maps two lists in list of maps.
     *
     * @param rsis        - list of RSI points.
     * @param priceChange - list of price deltas.
     * @return list of maps with data.
     */
    private List<List<Double>> map(List<RelativeStrengthIndicatorEntity> rsis,
                                   List<Double> priceChange) {
        var counter = Math.min(rsis.size(), priceChange.size());
        var result = new ArrayList<List<Double>>();
        for (var i = 0; i < counter; i++) {
            var point = new ArrayList<Double>();
            point.add(getGl(rsis.get(i)));
            point.add(priceChange.get(i));
            result.add(point);
        }
        return result;
    }

    /**
     * Calculate the attitude of gain sum to loss sum.
     *
     * @param rsi - relative strength indicator point.
     * @return the gain/loss attitude.
     */
    private Double getGl(RelativeStrengthIndicatorEntity rsi) {
        return rsi.getGainSumm() / rsi.getLossSumm();
    }

}
