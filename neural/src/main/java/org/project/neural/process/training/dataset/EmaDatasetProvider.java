package org.project.neural.process.training.dataset;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.CoreStockEntity;
import org.project.data.entities.indicators.AbsolutePriceOscillatorEntity;
import org.project.data.entities.indicators.ExponentialMovingAverageEntity;
import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.ProcessParamsService;
import org.project.data.services.interfaces.indicators.AbsolutePriceOscillatorService;
import org.project.data.services.interfaces.indicators.ExponentialMovingAverageService;
import org.project.model.Indicators;
import org.project.neural.process.training.dataset.delta.PriceChangeCalculator;
import org.project.neural.process.training.dataset.splitters.CoreStocksSplitter;
import org.project.neural.process.training.dataset.splitters.IndicatorSplitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class EmaDatasetProvider implements DatasetProvider {

    private final IndicatorSplitter<ExponentialMovingAverageEntity> indicatorSplitter;
    private final ExponentialMovingAverageService exponentialMovingAverageService;
    private final PriceChangeCalculator priceChangeCalculator;
    private final ProcessParamsService processParamsService;
    private final CoreStocksSplitter coreStocksSplitter;

    /**
     * Should provide a list of Lists. Each list for EMA contains:
     * priceChange - the price delta comparing previous datapoint;
     * ep - EMA to price attitude for each period;
     *
     * @param symbol - the name of the stock.
     * @param stocks - the list of stocks.
     * @return dataset.
     */
    @Override
    public List<List<Double>> getData(String symbol, List<CoreStockEntity> stocks) {
        var cacheDepthSeconds = processParamsService.getTrainInterval(symbol, Indicators.EMA);
        var intervalSeconds = processParamsService.getTrainInterval(symbol, Indicators.EMA);
        var allIndicators = exponentialMovingAverageService.findCache(symbol, cacheDepthSeconds);
        var indicators = indicatorSplitter.split(allIndicators, intervalSeconds);
        var filteredStocks = coreStocksSplitter.split(stocks, intervalSeconds);
        var priceChanges = priceChangeCalculator.getPriceChanges(filteredStocks);
        return map(indicators, priceChanges);
    }

    /**
     * Maps two lists in list of maps.
     *
     * @param emas        - list of EMA points.
     * @param priceChange - list of price deltas.
     * @return list of maps with data.
     */
    private List<List<Double>> map(List<ExponentialMovingAverageEntity> emas,
                                   List<Double> priceChange) {
        var counter = Math.min(emas.size(), priceChange.size());
        var result = new ArrayList<List<Double>>();
        for (var i = 0; i < counter; i++) {
            var point = new ArrayList<Double>();
            point.add(emas.get(i).getValue());
            point.add(priceChange.get(i));
            result.add(point);
        }
        return result;
    }

}
