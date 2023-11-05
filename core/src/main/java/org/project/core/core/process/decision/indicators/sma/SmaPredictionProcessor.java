package org.project.core.core.process.decision.indicators.sma;

import org.project.core.core.process.decision.indicators.IndicatorPredictionProcessor;

public class SmaPredictionProcessor implements IndicatorPredictionProcessor {

    /**
     * Returns the score in favor of opening new position
     * according to SMA price change prediction value.
     *
     * @param priceChangePrediction - last price change prediction
     * @return score
     */
    @Override
    public Long checkToOpenNewPosition(Double priceChangePrediction) {
        if (priceChangePrediction > 0) {
            return 1000L;
        }
        return 0L;
    }

    /**
     * Returns the score in favor of closing current position
     * according to SMA price change prediction value.
     *
     * @param priceChangePrediction - last price change prediction
     * @return score
     */
    @Override
    public Long checkToCloseCurrentPosition(Double priceChangePrediction) {
        if (priceChangePrediction < 0) {
            return 1000L;
        }
        return 0L;
    }
}
