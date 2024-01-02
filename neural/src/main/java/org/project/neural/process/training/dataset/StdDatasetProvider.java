package org.project.neural.process.training.dataset;

import lombok.RequiredArgsConstructor;
import org.cardinal.data.entities.CoreStockEntity;
import org.cardinal.data.entities.indicators.StandardDerivativesEntity;
import org.cardinal.data.services.interfaces.ProcessParamsService;
import org.cardinal.data.services.interfaces.indicators.StandardDerivativesService;
import org.project.model.Indicators;
import org.project.neural.process.training.dataset.delta.PriceChangeCalculator;
import org.project.neural.process.training.dataset.splitters.DataDateSplitter;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class StdDatasetProvider implements DatasetProvider {

    private final StandardDerivativesService standardDerivativesService;
    private final PriceChangeCalculator priceChangeCalculator;
    private final ProcessParamsService processParamsService;
    private final DataDateSplitter dataDateSplitter;

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
        var cacheDepthSeconds = processParamsService.getTrainCacheDepth(symbol, Indicators.STD);
        var intervalSeconds = processParamsService.getTrainInterval(symbol, Indicators.STD);
        var allIndicators = standardDerivativesService.findCache(symbol, cacheDepthSeconds);
        var indicators = dataDateSplitter.split(allIndicators, intervalSeconds);
        var filteredStocks = dataDateSplitter.split(stocks, intervalSeconds);
        var priceChanges = priceChangeCalculator.getPriceChanges(filteredStocks);
        return map(indicators, priceChanges);
    }

    /**
     * Maps two lists in list of maps.
     *
     * @param stds        - list of STD points.
     * @param priceChange - list of price deltas.
     * @return list of lists with data.
     */
    private List<List<Double>> map(List<StandardDerivativesEntity> stds,
                                   List<Double> priceChange) {
        var counter = Math.min(stds.size(), priceChange.size());
        var result = new ArrayList<List<Double>>();
        for (var i = 1; i < counter; i++) {
            var point = new ArrayList<Double>();
            point.add(priceChange.get(i - 1));
            point.add(getStdChange(stds.get(i - 1), stds.get(i)));
            result.add(point);
        }
        return result;
    }

    private Double getStdChange(StandardDerivativesEntity prev, StandardDerivativesEntity curr) {
        return curr.getValue() - prev.getValue();
    }

}
