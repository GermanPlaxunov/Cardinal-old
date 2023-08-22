package org.project.core.core.ml.neural;

import org.project.core.core.MathUtils;

import java.util.Random;

public class BasicNeuron implements Neuron {
    private Double bias;
    private Double weight1;
    private Double weight2;
    private Double oldBias;
    private Double oldWeight1;
    private Double oldWeight2;
    private Random random;

    public BasicNeuron(Random random) {
        this.random = random;
        bias = random.nextDouble(-1, 1);
        weight1 = random.nextDouble(-1, 1);
        weight2 = random.nextDouble(-1, 1);
        oldBias = bias;
        oldWeight1 = weight1;
        oldWeight2 = weight2;
    }

    private Double preActivation(double input1, double input2) {
        return (weight1 * input1) + (weight2 * input2) + bias;
    }

    @Override
    public Double compute(Double input1, Double input2) {
        var preActivation = preActivation(input1, input2);
        return MathUtils.sigmoid(preActivation);
    }

    @Override
    public void mutate() {
        var propertyToChange = random.nextInt(0, 3);
        var changeFactor = random.nextDouble(-1, 1);
        switch (propertyToChange) {
            case 0 -> bias += changeFactor;
            case 1 -> weight1 += changeFactor;
            case 2 -> weight2 += changeFactor;
        }
    }

    @Override
    public void forget(){
        bias = oldBias;
        weight1 = oldWeight1;
        weight2 = oldWeight2;
    }

    @Override
    public void remember(){
        oldBias = bias;
        oldWeight1 = weight1;
        oldWeight2 = weight2;
    }
}
