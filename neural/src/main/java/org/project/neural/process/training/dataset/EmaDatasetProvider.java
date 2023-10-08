package org.project.neural.process.training.dataset;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.CoreStockEntity;
import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.indicators.ExponentialMovingAverageService;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class EmaDatasetProvider implements DatasetProvider {

    private final ExponentialMovingAverageService exponentialMovingAverageService;
    private final CoreStockService coreStockService;

    /**
     * Should provide a list of Maps. Each entry for EMA contains:
     * priceChange - the price delta comparing previous datapoint;
     * ep - EMA to price attitude for each period;
     *
     * @param symbol - the name of the stock.
     * @param stocks - the list of stocks.
     * @return dataset.
     */
    @Override
    public List<List<Double>> getData(String symbol, List<CoreStockEntity> stocks) {
        return null;
    }
}
