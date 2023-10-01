package org.project.neural.process.training.dataset;

import lombok.RequiredArgsConstructor;
import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.indicators.BollingerBandsService;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class BbandDatasetProvider implements DatasetProvider {

    private final BollingerBandsService bollingerBandsService;
    private final CoreStockService coreStockService;

    /**
     * Should provide a list of Maps. Each entry for BBAND contains:
     * priceChange - the price delta comparing previous datapoint;
     * tm - top to middle attitude;
     * bm - bottom to middle attitude;
     *
     * @param symbol - the name of the stock.
     * @return dataset.
     */
    @Override
    public List<Map<String, Double>> getData(String symbol) {
        return null;
    }
}
