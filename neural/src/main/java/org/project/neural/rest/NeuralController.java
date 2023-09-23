package org.project.neural.rest;

public interface NeuralController {

    Double predict(String symbol, String indicator);

    void train(String symbol);

}
