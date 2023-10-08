package org.project.neural.process.training.dataset;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.CoreStockEntity;
import org.project.data.entities.indicators.BollingerBandsEntity;
import org.project.data.services.interfaces.ProcessParamsService;
import org.project.data.services.interfaces.indicators.BollingerBandsService;
import org.project.model.Indicators;
import org.project.neural.process.training.dataset.delta.PriceChangeCalculator;
import org.project.neural.process.training.dataset.splitters.CoreStocksSplitter;
import org.project.neural.process.training.dataset.splitters.IndicatorSplitter;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BbandDatasetProvider implements DatasetProvider {

    private final IndicatorSplitter<BollingerBandsEntity> indicatorSplitter;
    private final BollingerBandsService bollingerBandsService;
    private final PriceChangeCalculator priceChangeCalculator;
    private final ProcessParamsService processParamsService;
    private final CoreStocksSplitter coreStocksSplitter;

    /**
     * Should provide a list of lists. Each list for BBAND contains:
     * priceChange - the price delta comparing previous datapoint;
     * tm - top to middle attitude;
     * bm - bottom to middle attitude;
     *
     * @param symbol - the name of the stock.
     * @param stocks - the list of stocks.
     * @return dataset.
     */
    @Override
    public List<List<Double>> getData(String symbol, List<CoreStockEntity> stocks) {
        var cacheDepthSeconds = processParamsService.getTrainInterval(symbol, Indicators.BBAND);
        var intervalSeconds = processParamsService.getTrainInterval(symbol, Indicators.BBAND);
        var allIndicators = bollingerBandsService.findCache(symbol, cacheDepthSeconds);
        var indicators = indicatorSplitter.split(allIndicators, intervalSeconds);
        var filteredStocks = coreStocksSplitter.split(stocks, intervalSeconds);
        var priceChanges = priceChangeCalculator.getPriceChanges(filteredStocks);
        return map(indicators, priceChanges);
    }

    /**
     * Maps two lists in list of lists.
     *
     * @param bbands      - list of bbands.
     * @param priceChange - list of price deltas.
     * @return list of maps with data.
     */
    private List<List<Double>> map(List<BollingerBandsEntity> bbands,
                                   List<Double> priceChange) {
        var counter = Math.min(bbands.size(), priceChange.size());
        var result = new ArrayList<List<Double>>();
        for (var i = 0; i < counter; i++) {
            var point = new ArrayList<Double>();
            point.add(getTm(bbands.get(i)));
            point.add(getBm(bbands.get(i)));
            point.add(priceChange.get(i));
            result.add(point);
        }
        return result;
    }

    /**
     * Calculate the attitude between TOP and MIDDLE bands.
     *
     * @param bband - Bollinger band point.
     * @return the attitude.
     */
    private Double getTm(BollingerBandsEntity bband) {
        return bband.getUpper() / bband.getMiddle();
    }

    /**
     * Calculate the attitude between BOTTOM and MIDDLE bands.
     *
     * @param bband - Bollinger band point.
     * @return the attitude.
     */
    private Double getBm(BollingerBandsEntity bband) {
        return bband.getLower() / bband.getMiddle();
    }

}
