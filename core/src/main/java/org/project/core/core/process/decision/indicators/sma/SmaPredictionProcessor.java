package org.project.core.core.process.decision.indicators.sma;

import org.project.core.core.process.decision.indicators.IndicatorPredictionProcessor;

public class SmaPredictionProcessor implements IndicatorPredictionProcessor {
    @Override
    public Long checkToOpenNewPosition(Double priceChangePrediction) {
        if (priceChangePrediction > 0) {
            return 1000L;
        }
        return 0L;
    }

    @Override
    public Long checkToCloseCurrentPosition(Double priceChangePrediction) {
        if (priceChangePrediction < 0) {
            return 1000L;
        }
        return 0L;
    }
}
