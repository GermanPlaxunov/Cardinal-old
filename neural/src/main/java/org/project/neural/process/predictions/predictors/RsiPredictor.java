package org.project.neural.process.predictions.predictors;

import org.project.data.entities.indicators.RelativeStrengthEntityDataItem;
import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.ProcessParamsService;
import org.project.data.services.interfaces.indicators.RelativeStrengthIndicatorService;
import org.project.model.Indicators;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.predictions.Predictor;

public class RsiPredictor extends AbstractPredictor implements Predictor {

    private final RelativeStrengthIndicatorService relativeStrengthIndicatorService;
    private final NetworkStore networkStore;

    public RsiPredictor(RelativeStrengthIndicatorService relativeStrengthIndicatorService,
                        ProcessParamsService processParamsService,
                        CoreStockService coreStockService,
                        NetworkStore networkStore) {
        super(processParamsService, coreStockService);
        this.relativeStrengthIndicatorService = relativeStrengthIndicatorService;
        this.networkStore = networkStore;
    }

    public Double predict(String symbol) {
        var network = networkStore.get(Indicators.RSI, symbol);
        var rsi = relativeStrengthIndicatorService.findLast(symbol);
        var prevPriceChange = getPrevPriceChange(symbol);
        var result = network.predict(getGl(rsi), prevPriceChange);
        return result;
    }

    private Double getGl(RelativeStrengthEntityDataItem rsi) {
        return rsi.getGainSumm() / rsi.getLossSumm();
    }

}
