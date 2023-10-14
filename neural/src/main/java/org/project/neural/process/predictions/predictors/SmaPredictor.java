package org.project.neural.process.predictions.predictors;

import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.ProcessParamsService;
import org.project.data.services.interfaces.indicators.SimpleMovingAverageService;
import org.project.model.Indicators;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.predictions.Predictor;

public class SmaPredictor extends AbstractPredictor implements Predictor {

    private final SimpleMovingAverageService simpleMovingAverageService;
    private final NetworkStore networkStore;

    public SmaPredictor(SimpleMovingAverageService simpleMovingAverageService,
                        ProcessParamsService processParamsService,
                        CoreStockService coreStockService,
                        NetworkStore networkStore) {
        super(processParamsService, coreStockService);
        this.simpleMovingAverageService = simpleMovingAverageService;
        this.networkStore = networkStore;
    }

    @Override
    public Double predict(String symbol) {
        var network = networkStore.get(Indicators.SMA, symbol);
        var sma = simpleMovingAverageService.findLast(symbol);
        var prevPriceChange = getPrevPriceChange(symbol);
        var prediction = network.predict(sma.getValue(), prevPriceChange);
        return prediction;
    }
}
