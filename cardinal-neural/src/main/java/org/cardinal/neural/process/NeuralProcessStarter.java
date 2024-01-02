package org.cardinal.neural.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cardinal.neural.process.predictions.PredictorsStore;
import org.cardinal.neural.process.training.TrainParamsProvider;
import org.cardinal.neural.process.training.TrainersStore;
import org.cardinal.neural.process.training.verification.NeuralNetworkTesting;
import org.project.model.Indicators;

@Slf4j
@RequiredArgsConstructor
public class NeuralProcessStarter {

    private final NeuralNetworkTesting neuralNetworkTesting;
    private final TrainParamsProvider trainParamsProvider;
    private final PredictorsStore predictorsStore;
    private final TrainersStore trainersStore;

    public void train(String symbol) {
        var params = trainParamsProvider.getTrainParams(symbol);
        for (var indicator : Indicators.values()) {
            log.info("Start training {} network for {}", indicator.name(), symbol);
            var trainer = trainersStore.get(indicator);
            trainer.train(params);
            var totalMse = neuralNetworkTesting.test(symbol, indicator);
            log.info("Total MSE for {} {} = {}", indicator, symbol, totalMse);
        }
    }

    public Double predict(String symbol, String indicatorName) {
        var indicator = Indicators.getFromName(indicatorName);
        var prediction = predictorsStore.get(indicator)
                .predict(symbol);
        return prediction;
    }
}
