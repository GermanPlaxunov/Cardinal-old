package org.project.neural.process.predictions;

public interface Predictor {

    /**
     * Predicts the stock price change using provided
     * input values of corresponding indicator.
     *
     * @param symbol - the name of the stock.
     * @return predicted price change.
     */
    Double predict(String symbol);

}
