package org.project.neural.process.training.dataset;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.CoreStockEntity;
import org.project.data.entities.indicators.SimpleMovingAverageEntity;
import org.project.data.services.interfaces.ProcessParamsService;
import org.project.data.services.interfaces.indicators.SimpleMovingAverageService;
import org.project.model.Indicators;
import org.project.neural.process.training.dataset.delta.PriceChangeCalculator;
import org.project.neural.process.training.dataset.splitters.CoreStocksSplitter;
import org.project.neural.process.training.dataset.splitters.IndicatorSplitter;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class SmaDatasetProvider implements DatasetProvider {

    private final IndicatorSplitter<SimpleMovingAverageEntity> indicatorSplitter;
    private final SimpleMovingAverageService simpleMovingAverageService;
    private final PriceChangeCalculator priceChangeCalculator;
    private final ProcessParamsService processParamsService;
    private final CoreStocksSplitter coreStocksSplitter;

    /**
     * Should provide a list of Maps. Each entry for SMA contains:
     * priceChange - the price delta comparing previous datapoint;
     * sp - SMA to price attitude for each period;
     *
     * @param symbol - the name of the stock.
     * @param stocks - the list of stocks.
     * @return dataset.
     */
    @Override
    public List<List<Double>> getData(String symbol, List<CoreStockEntity> stocks) {
        var cacheDepthSeconds = processParamsService.getTrainInterval(symbol, Indicators.SMA);
        var intervalSeconds = processParamsService.getTrainInterval(symbol, Indicators.SMA);
        var allIndicators = simpleMovingAverageService.findCache(symbol, cacheDepthSeconds);
        var indicators = indicatorSplitter.split(allIndicators, intervalSeconds);
        var filteredStocks = coreStocksSplitter.split(stocks, intervalSeconds);
        var priceChanges = priceChangeCalculator.getPriceChanges(filteredStocks);
        return map(indicators, priceChanges, filteredStocks);
    }

    /**
     * Maps two lists in list of maps.
     *
     * @param smas        - list of SMA points.
     * @param priceChange - list of price deltas.
     * @param stocks      - list of stocks.
     * @return list of maps with data.
     */
    private List<List<Double>> map(List<SimpleMovingAverageEntity> smas,
                                   List<Double> priceChange,
                                   List<CoreStockEntity> stocks) {
        var counter = Math.min(smas.size(), priceChange.size());
        var result = new ArrayList<List<Double>>();
        for (var i = 0; i < counter; i++) {
            var point = new ArrayList<Double>();
            point.add(getSp(smas.get(i), stocks.get(i).getClose()));
            point.add(priceChange.get(i));
            result.add(point);
        }
        return result;
    }

    /**
     * Calculates the SMA to price attitude.
     *
     * @param sma   - simple moving average.
     * @param price - price of the stock.
     * @return the SMA to price attitude.
     */
    private Double getSp(SimpleMovingAverageEntity sma, Double price) {
        return sma.getValue() / price;
    }

}