package org.project.neural.process.rest;

public interface NeuralController {

    Double predict(String symbol);

    void train(String symbol);

}
