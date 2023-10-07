package org.project.neural.process.training.dataset;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.CoreStockEntity;
import org.project.data.entities.indicators.AbsolutePriceOscillatorEntity;
import org.project.data.services.interfaces.ProcessParamsService;
import org.project.data.services.interfaces.indicators.AbsolutePriceOscillatorService;
import org.project.model.Indicators;
import org.project.neural.process.training.dataset.delta.PriceChangeCalculator;
import org.project.neural.process.training.dataset.splitters.CoreStocksSplitter;
import org.project.neural.process.training.dataset.splitters.IndicatorSplitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ApoDatasetProvider implements DatasetProvider {

    private final IndicatorSplitter<AbsolutePriceOscillatorEntity> indicatorSplitter;
    private final AbsolutePriceOscillatorService absolutePriceOscillatorService;
    private final PriceChangeCalculator priceChangeCalculator;
    private final ProcessParamsService processParamsService;
    private final CoreStocksSplitter coreStocksSplitter;

    /**
     * Should provide a list of Maps. Each entry for APO contains:
     * priceChange - the price delta comparing previous datapoint;
     * apo - absolute price oscillator for each period;
     *
     * @param symbol - the name of the stock.
     * @param stocks - the list of the stocks.
     * @return dataset.
     */
    @Override
    public List<Map<String, Double>> getData(String symbol, List<CoreStockEntity> stocks) {
        var cacheDepthSeconds = processParamsService.getTrainInterval(symbol, Indicators.APO);
        var intervalSeconds = processParamsService.getTrainInterval(symbol, Indicators.APO);
        var allIndicators = absolutePriceOscillatorService.findCache(symbol, cacheDepthSeconds);
        var indicators = indicatorSplitter.split(allIndicators, intervalSeconds);
        var filteredStocks = coreStocksSplitter.split(stocks, intervalSeconds);
        var priceChanges = priceChangeCalculator.getPriceChanges(filteredStocks);
        return map(indicators, priceChanges);
    }

    /**
     * Maps two lists in list of maps.
     *
     * @param apos        - list of apos.
     * @param priceChange - list of price deltas.
     * @return list of maps with data.
     */
    private List<Map<String, Double>> map(List<AbsolutePriceOscillatorEntity> apos,
                                          List<Double> priceChange) {
        var counter = Math.min(apos.size(), priceChange.size());
        var result = new ArrayList<Map<String, Double>>();
        for (var i = 0; i < counter; i++) {
            var map = new HashMap<String, Double>();
            map.put("APO", apos.get(i).getValue());
            map.put("priceChange", priceChange.get(i));
            result.add(map);
        }
        return result;
    }

}
