package org.project.neural.rest;

public interface NeuralController {

    Double predict(String symbol);

    void train(String symbol);

}
