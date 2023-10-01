package org.project.neural.process.training.dataset;

import lombok.RequiredArgsConstructor;
import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.indicators.AbsolutePriceOscillatorService;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ApoDatasetProvider implements DatasetProvider {

    private final AbsolutePriceOscillatorService absolutePriceOscillatorService;
    private final CoreStockService coreStockService;

    /**
     * Should provide a list of Maps. Each entry for APO contains:
     * priceChange - the price delta comparing previous datapoint;
     * apo - absolute price oscillator for each period;
     *
     * @param symbol - the name of the stock.
     * @return dataset.
     */
    @Override
    public List<Map<String, Double>> getData(String symbol) {
        return null;
    }

}
