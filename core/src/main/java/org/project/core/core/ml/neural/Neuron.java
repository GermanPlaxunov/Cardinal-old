package org.project.core.core.ml.neural;

public interface Neuron {

    Double compute(Double input1, Double input2);

    void mutate();

    void forget();

    void remember();
}
