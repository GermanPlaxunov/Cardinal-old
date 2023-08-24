package org.project.core.core.ml.neural;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.project.core.core.MathUtils.meanSquareLoss;

@Slf4j
public class BasicNeuralNetwork {

    private List<Neuron> neurons;
    private String name;

    public BasicNeuralNetwork(String name) {
        this.name = name;
        var random = new Random();
        neurons = new ArrayList<>() {{
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

    public void train(List<List<Double>> data, List<Double> answers, Integer epochs) {
        Double bestEpochLoss = null;
        for (var epoch = 0; epoch < epochs; epoch++) {
            // adapt neuron
            var epochNeuron = neurons.get(epoch % neurons.size());
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
                log.debug("Epoch: {} | bestEpochLoss: {} | thisEpochLoss: {}", epoch, bestEpochLoss, thisEpochLoss);
            }
        }
    }
}
