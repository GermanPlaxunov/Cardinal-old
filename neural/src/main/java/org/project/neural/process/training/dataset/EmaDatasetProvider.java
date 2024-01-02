package org.project.neural.process.training.dataset;

import lombok.RequiredArgsConstructor;
import org.cardinal.data.entities.CoreStockEntity;
import org.cardinal.data.entities.indicators.ExponentialMovingAverageEntity;
import org.cardinal.data.services.interfaces.ProcessParamsService;
import org.cardinal.data.services.interfaces.indicators.ExponentialMovingAverageService;
import org.project.model.Indicators;
import org.project.neural.process.training.dataset.delta.PriceChangeCalculator;
import org.project.neural.process.training.dataset.splitters.DataDateSplitter;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class EmaDatasetProvider implements DatasetProvider {

    private final ExponentialMovingAverageService exponentialMovingAverageService;
    private final PriceChangeCalculator priceChangeCalculator;
    private final ProcessParamsService processParamsService;
    private final DataDateSplitter dataDateSplitter;

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
        var cacheDepthSeconds = processParamsService.getTrainCacheDepth(symbol, Indicators.EMA);
        var intervalSeconds = processParamsService.getTrainInterval(symbol, Indicators.EMA);
        var allIndicators = exponentialMovingAverageService.findCache(symbol, cacheDepthSeconds);
        var indicators = dataDateSplitter.split(allIndicators, intervalSeconds);
        var filteredStocks = dataDateSplitter.split(stocks, intervalSeconds);
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
            point.add(priceChange.get(i));
            point.add(emas.get(i).getValue());
            result.add(point);
        }
        return result;
    }

}
