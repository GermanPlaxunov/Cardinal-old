package org.cardinal.neural.process.network;

import lombok.Getter;

import java.util.Random;

import static org.cardinal.neural.process.Utils.sigmoid;

public class SimpleNeuron {
    @Getter
    private Double bias;
    @Getter
    private Double weight1;
    @Getter
    private Double weight2;
    private Double oldBias;
    private Double oldWeight1;
    private Double oldWeight2;
    private Random random;

    public SimpleNeuron(Double bias, Double weight1, Double weight2, Random random) {
        this.bias = bias;
        this.weight1 = weight1;
        this.weight2 = weight2;
        this.oldBias = bias;
        this.oldWeight1 = weight1;
        this.oldWeight2 = weight2;
        this.random = random;
    }

    public SimpleNeuron(Random random) {
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

    public Double compute(Double input1, Double input2) {
        var preActivation = preActivation(input1, input2);
        return sigmoid(preActivation);
    }

    public void mutate() {
        var propertyToChange = random.nextInt(0, 3);
        var changeFactor = random.nextDouble(-1, 1);
        switch (propertyToChange) {
            case 0 -> bias += changeFactor;
            case 1 -> weight1 += changeFactor;
            case 2 -> weight2 += changeFactor;
        }
    }

    public void forget() {
        bias = oldBias;
        weight1 = oldWeight1;
        weight2 = oldWeight2;
    }

    public void remember() {
        oldBias = bias;
        oldWeight1 = weight1;
        oldWeight2 = weight2;
    }
}
