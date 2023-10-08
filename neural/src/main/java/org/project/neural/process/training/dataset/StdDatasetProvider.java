package org.project.neural.process.training.dataset;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.CoreStockEntity;
import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.indicators.StandardDerivativesService;

import java.util.List;

@RequiredArgsConstructor
public class StdDatasetProvider implements DatasetProvider {

    private final StandardDerivativesService standardDerivativesService;
    private final CoreStockService coreStockService;

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
        return null;
    }
}
