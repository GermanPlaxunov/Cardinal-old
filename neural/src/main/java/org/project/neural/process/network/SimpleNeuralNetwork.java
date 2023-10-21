package org.project.neural.process.network;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.project.model.Indicators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.project.neural.process.Utils.meanSquareLoss;

@Data
@Slf4j
public class SimpleNeuralNetwork {

    private List<SimpleNeuron> neurons;
    private String name;

    public SimpleNeuralNetwork(String name, List<SimpleNeuron> neurons) {
        this.name = name;
        this.neurons = neurons;
    }

    public SimpleNeuralNetwork(String name) {
        this.name = name;
        var random = new Random();
        neurons = new ArrayList<>() {{
            add(new SimpleNeuron(random));
            add(new SimpleNeuron(random));
            add(new SimpleNeuron(random));
            add(new SimpleNeuron(random));
            add(new SimpleNeuron(random));
            add(new SimpleNeuron(random));
            add(new SimpleNeuron(random));
            add(new SimpleNeuron(random));
            add(new SimpleNeuron(random));
            add(new SimpleNeuron(random));
        }};
    }

    public Double predict(Double input1, Double input2) {
        return neurons.get(9).compute(
                neurons.get(7).compute(
                        neurons.get(4).compute(
                                neurons.get(0).compute(input1, input2),
                                neurons.get(1).compute(input1, input2)
                        ),
                        neurons.get(5).compute(
                                neurons.get(1).compute(input1, input2),
                                neurons.get(2).compute(input1, input2)
                        )
                ),
                neurons.get(8).compute(
                        neurons.get(5).compute(
                                neurons.get(1).compute(input1, input2),
                                neurons.get(2).compute(input1, input2)
                        ),
                        neurons.get(6).compute(
                                neurons.get(2).compute(input1, input2),
                                neurons.get(3).compute(input1, input2)
                        )
                )
        );
    }

    public Double getMeanSquaredLoss(List<List<Double>> data, List<Double> answers) {
        var predictions = new ArrayList<Double>();
        for (int i = 0; i < data.size(); i++) {
            predictions.add(i, predict(data.get(i).get(0), data.get(i).get(1)));
        }
        return meanSquareLoss(answers, predictions);
    }

    /**
     * Method to train neural network on dataset.
     *
     * @param data - dataset.
     * @param answers - prepared answers.
     * @param epochs - amount of epochs.
     */
    public void train(String symbol, List<List<Double>> data, List<Double> answers, Long epochs) {
        var beforeTrainingMSL = getMeanSquaredLoss(data, answers);
        Double bestEpochLoss = null;
        for (var epoch = 0; epoch < epochs; epoch++) {
            var epochNeuron = neurons.get(epoch % neurons.size());
            epochNeuron.mutate();

            var thisEpochLoss = getMeanSquaredLoss(data, answers);

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
        }
        var afterTrainingMSL = getMeanSquaredLoss(data, answers);
        log.info("{} - {} Network loss decrement: {}", symbol, Indicators.APO.name(), beforeTrainingMSL - afterTrainingMSL);
    }
}
