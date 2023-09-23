package org.project.core.core.process.decision.indicators;

public interface IndicatorPredictionProcessor {

    /**
     * Returns score from 0 to 1000 based on Neural network prediction.
     * If score between 0 and 500 than network assumes that position shouldn`t
     * be opened at current moment.
     * If score between 500 and 1000 than network assumes that position should
     * be opened wright now.
     * Then further score from the 500 then stronger the signal to open/close
     * position.
     */
    Long checkToOpenNewPosition(Double priceChangePrediction);

    /**
     * Returns score from 0 to 1000 based on Neural network prediction.
     * If score between 0 and 500 than network assumes that position shouldn`t
     * be closed at current moment.
     * If score between 500 and 1000 than network assumes that position should
     * be closed wright now.
     * Then further score from the 500 then stronger the signal to open/close
     * position.
     */
    Long checkToCloseCurrentPosition(Double priceChangePrediction);

}
