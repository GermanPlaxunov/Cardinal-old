package org.project.neural.process.training.dataset;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.CoreStockEntity;
import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.indicators.RelativeStrengthIndicatorService;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class RsiDatasetProvider implements DatasetProvider {

    private final RelativeStrengthIndicatorService relativeStrengthIndicatorService;
    private final CoreStockService coreStockService;

    /**
     * Should provide a list of Maps. Each entry for RSI contains:
     * priceChange - the price delta comparing previous datapoint;
     * gl - gain to loss attitude for each period;
     *
     * @param symbol - the name of the stock.
     * @param stocks - the list of stocks.
     * @return dataset.
     */
    @Override
    public List<Map<String, Double>> getData(String symbol, List<CoreStockEntity> stocks) {
        return null;
    }
}
