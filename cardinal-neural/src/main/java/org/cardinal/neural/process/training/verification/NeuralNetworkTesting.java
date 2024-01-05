package org.cardinal.neural.process.training.verification;

import lombok.RequiredArgsConstructor;
import org.cardinal.data.services.interfaces.CoreStockService;
import org.cardinal.data.services.interfaces.ProcessParamsService;
import org.cardinal.model.Indicators;
import org.cardinal.neural.process.Utils;
import org.cardinal.neural.process.network.NetworkStore;
import org.cardinal.neural.process.network.SimpleNeuralNetwork;
import org.cardinal.neural.process.training.dataset.DatasetProviders;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class NeuralNetworkTesting {

    private final ProcessParamsService processParamsService;
    private final CoreStockService coreStockService;
    private final DatasetProviders datasetProviders;
    private final NetworkStore networkStore;

    /**
     * Should test network accuracy. Receives the
     * network and dataset using symbol and indicator,
     * obtains test dataset and predicts for every point price change.
     * Get the MSE of every prediction and return their sum.
     *
     * @param symbol    - the name of the stock.
     * @param indicator - the name of the indicator.
     * @return total MSE for dataset.
     */
    public Double test(String symbol, Indicators indicator) {
        var depth = processParamsService.getVerifyDatasetDepth(symbol, indicator);
        var network = networkStore.get(indicator, symbol);
        var datasetProvider = datasetProviders.get(indicator);
        var stocks = coreStockService.findCache(symbol, depth);
        var dataset = datasetProvider.getData(symbol, stocks);
        var answers = datasetProvider.extractAnswers(dataset);
        var questions = datasetProvider.getQuestions(dataset);
        var predictions = predict(network, questions);
        return Utils.meanSquareLoss(answers, predictions);
    }

    /**
     * Makes predictions for all questions and collect them in list.
     *
     * @param network   - neural network instance.
     * @param questions - list of questions.
     * @return list of predictions.
     */
    private List<Double> predict(SimpleNeuralNetwork network, List<List<Double>> questions) {
        var predictions = new ArrayList<Double>();
        for (var i = 0; i < questions.size() - 1; i++) { // -1 because we don`t have answer for the latest stock yet
            predictions.add(network.predict(questions.get(i).get(0), questions.get(i).get(1)));
        }
        return predictions;
    }

}
