package org.project.neural.process.rest;

public interface NeuralController {

    void predict(String symbol);

    void train(String symbol);

}
