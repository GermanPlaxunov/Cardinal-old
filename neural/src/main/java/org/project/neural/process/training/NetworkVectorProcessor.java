package org.project.neural.process.training;

import org.project.neural.process.network.SimpleNeuralNetwork;
import org.project.neural.process.network.SimpleNeuron;

import java.util.Arrays;
import java.util.Random;

public class NetworkVectorProcessor {
    private final String NAME_DIVIDER = "<=>";
    private final String DIVIDER = "=>";
    private final String END = ";";

    public String getVector(SimpleNeuralNetwork network) {
        var vector = new StringBuilder()
                .append(network.getName())
                .append(NAME_DIVIDER);
        network.getNeurons()
                .stream()
                .forEach(neuron -> vector.append(getNeuronVector(neuron)));
        return vector.toString();
    }

    public SimpleNeuralNetwork buildNetworkFromVector(String vector) {
        var name = vector.split(NAME_DIVIDER)[0];
        var random = new Random();
        var neurons = Arrays.stream(vector.split(NAME_DIVIDER)[1]
                        .split(END))
                .map(nVector -> buildNeuronFromVector(nVector, random))
                .toList();
        return new SimpleNeuralNetwork(name, neurons);
    }

    private String getNeuronVector(SimpleNeuron neuron) {
        return new StringBuilder()
                .append(neuron.getBias())
                .append(DIVIDER)
                .append(neuron.getWeight1())
                .append(DIVIDER)
                .append(neuron.getWeight2())
                .append(END)
                .toString();
    }

    private SimpleNeuron buildNeuronFromVector(String vector, Random random) {
        var data = vector.split(DIVIDER);
        var bias = Double.parseDouble(data[0]);
        var weight1 = Double.parseDouble(data[1]);
        var weight2 = Double.parseDouble(data[2]);
        return new SimpleNeuron(bias, weight1, weight2, random);
    }

}
