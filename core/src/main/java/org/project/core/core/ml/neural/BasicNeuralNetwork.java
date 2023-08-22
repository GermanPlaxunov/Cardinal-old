package org.project.core.core.ml.neural;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.project.core.core.MathUtils.meanSquareLoss;

public class BasicNeuralNetwork {

    private List<Neuron> neurons;

    public BasicNeuralNetwork() {
        var random = new Random();
        neurons = new ArrayList<>(){{
           add(new BasicNeuron(random));
           add(new BasicNeuron(random));
           add(new BasicNeuron(random));
           add(new BasicNeuron(random));
           add(new BasicNeuron(random));
           add(new BasicNeuron(random));
        }};
    }

    public Double predict(Double input1, Double input2) {
        return neurons.get(5).compute(
                neurons.get(4).compute(
                        neurons.get(2).compute(input1, input2),
                        neurons.get(1).compute(input1, input2)
                ),
                neurons.get(3).compute(
                        neurons.get(1).compute(input1, input2),
                        neurons.get(0).compute(input1, input2)
                )
        );
    }

    public void train(List<List<Double>> data, List<Double> answers) {
        Double bestEpochLoss = null;
        for (var epoch = 0; epoch < 1000; epoch++) {
            // adapt neuron
            var epochNeuron = neurons.get(epoch % 6);
            epochNeuron.mutate();

            var predictions = new ArrayList<Double>();
            for (int i = 0; i < data.size(); i++) {
                predictions.add(i, predict(data.get(i).get(0), data.get(i).get(1)));
            }
            var thisEpochLoss = meanSquareLoss(answers, predictions);

            if (bestEpochLoss == null) {
                bestEpochLoss = thisEpochLoss;
                epochNeuron.remember();
            } else {
                if (thisEpochLoss < bestEpochLoss) {
                    bestEpochLoss = thisEpochLoss;
                    epochNeuron.remember();
                } else {
                    epochNeuron.forget();
                }
            }
            if (epoch % 100 == 0) {
                System.out.println(String.format("Epoch: %s | bestEpochLoss: %.15f | thisEpochLoss: %.15f", epoch, bestEpochLoss, thisEpochLoss));
            }
        }
    }
}
