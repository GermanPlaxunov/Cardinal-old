package org.project.neural.process.training.dataset;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.CoreStockEntity;
import org.project.data.entities.indicators.StandardDerivativesEntity;
import org.project.data.services.interfaces.ProcessParamsService;
import org.project.data.services.interfaces.indicators.StandardDerivativesService;
import org.project.model.Indicators;
import org.project.neural.process.training.dataset.delta.PriceChangeCalculator;
import org.project.neural.process.training.dataset.splitters.CoreStocksSplitter;
import org.project.neural.process.training.dataset.splitters.IndicatorSplitter;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class StdDatasetProvider implements DatasetProvider {

    private final IndicatorSplitter<StandardDerivativesEntity> indicatorSplitter;
    private final StandardDerivativesService standardDerivativesService;
    private final PriceChangeCalculator priceChangeCalculator;
    private final ProcessParamsService processParamsService;
    private final CoreStocksSplitter coreStocksSplitter;

    /**
     * Should provide a list of Maps. Each entry for STD contains:
     * priceChange - the price delta comparing previous datapoint;
     * stdChange - STD change for each period;
     *
     * @param symbol - the name of the stock.
     * @param stocks - the list of stocks.
     * @return dataset.
     */
    @Override
    public List<List<Double>> getData(String symbol, List<CoreStockEntity> stocks) {
        var cacheDepthSeconds = processParamsService.getTrainInterval(symbol, Indicators.STD);
        var intervalSeconds = processParamsService.getTrainInterval(symbol, Indicators.STD);
        var allIndicators = standardDerivativesService.findCache(symbol, cacheDepthSeconds);
        var indicators = indicatorSplitter.split(allIndicators, intervalSeconds);
        var filteredStocks = coreStocksSplitter.split(stocks, intervalSeconds);
        var priceChanges = priceChangeCalculator.getPriceChanges(filteredStocks);
        return map(indicators, priceChanges);
    }

    /**
     * Maps two lists in list of maps.
     *
     * @param stds        - list of STD points.
     * @param priceChange - list of price deltas.
     * @return list of maps with data.
     */
    private List<List<Double>> map(List<StandardDerivativesEntity> stds,
                                   List<Double> priceChange) {
        var counter = Math.min(stds.size(), priceChange.size());
        var result = new ArrayList<List<Double>>();
        for (var i = 1; i < counter; i++) {
            var point = new ArrayList<Double>();
            point.add(getStdChange(stds.get(i - 1), stds.get(i)));
            point.add(priceChange.get(i - 1));
            result.add(point);
        }
        return result;
    }

    private Double getStdChange(StandardDerivativesEntity prev, StandardDerivativesEntity curr) {
        return curr.getValue() - prev.getValue();
    }

}