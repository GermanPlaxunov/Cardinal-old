package org.project.neural.process;

import lombok.RequiredArgsConstructor;
import org.project.model.Indicators;
import org.project.neural.process.predictions.predictors.RsiPredictor;
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

    public Double predict(String symbol, String indicatorName) {
        var indicator = Indicators.getFromName(indicatorName);
        var prediction = switch (indicator) {
            case APO -> stubPrediction();
            case BBAND -> stubPrediction();
            case EMA -> stubPrediction();
            case RSI -> rsiPredictor.predict(symbol);
            case SMA -> stubPrediction();
            case STD -> stubPrediction();
        };
        return prediction;
    }

    private Double stubPrediction() {
        return 0.0;
    }
}
