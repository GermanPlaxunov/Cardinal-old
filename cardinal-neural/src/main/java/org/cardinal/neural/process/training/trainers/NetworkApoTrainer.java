package org.cardinal.neural.process.training.trainers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cardinal.neural.process.training.training.TrainParams;
import org.cardinal.model.Indicators;
import org.cardinal.neural.process.network.NetworkStore;
import org.cardinal.neural.process.training.NetworkTrainer;
import org.cardinal.neural.process.training.dataset.DatasetProviders;

@Slf4j
@RequiredArgsConstructor
public class NetworkApoTrainer extends AbstractTrainer implements NetworkTrainer {

    private final DatasetProviders datasetProviders;
    private final NetworkStore networkStore;

    @Override
    public void train(TrainParams params) {
        var symbol = params.getSymbol();
        log.info("Start training APO network for {}", symbol);
        var stocks = params.getStocks();
        var datasetProvider = datasetProviders.get(Indicators.APO);
        var network = networkStore.get(Indicators.APO, symbol);
        var dataset = datasetProvider.getData(symbol, stocks);
        var answers = datasetProvider.extractAnswers(dataset);
        var epochs = params.getEpochs();
        removeLastElement(dataset);
        network.train(symbol, dataset, answers, epochs);
        networkStore.updateNetwork(Indicators.APO, symbol, network);
    }

}
