package org.project.neural.process;

import lombok.RequiredArgsConstructor;
import org.project.neural.process.predictions.RsiPredictor;
import org.project.neural.process.training.NetworkRsiTrainer;
import org.project.neural.process.training.TrainParamsProvider;

@RequiredArgsConstructor
public class NeuralProcessStarter {
    private final TrainParamsProvider trainParamsProvider;
    private final NetworkRsiTrainer networkRsiTrainer;
    private final RsiPredictor rsiPredictor;

    public void train(String symbol) {
        var params = trainParamsProvider.getTrainParams(symbol);
        networkRsiTrainer.train(params);
    }

    public Double predict(String symbol) {
        return rsiPredictor.predict(symbol);
    }
}
