package org.project.neural.process;

import lombok.RequiredArgsConstructor;
import org.project.model.Indicators;
import org.project.neural.process.predictions.PredictorsStore;
import org.project.neural.process.training.TrainParamsProvider;
import org.project.neural.process.training.TrainersStore;

@RequiredArgsConstructor
public class NeuralProcessStarter {
    private final TrainParamsProvider trainParamsProvider;
    private final PredictorsStore predictorsStore;
    private final TrainersStore trainersStore;

    public void train(String symbol) {
        var params = trainParamsProvider.getTrainParams(symbol);
        trainersStore.get(Indicators.RSI)
                .train(params);
    }

    public Double predict(String symbol, String indicatorName) {
        var indicator = Indicators.getFromName(indicatorName);
        var prediction = predictorsStore.get(indicator)
                .predict(symbol);
        return prediction;
    }

    private Double stubPrediction() {
        return 0.0;
    }
}