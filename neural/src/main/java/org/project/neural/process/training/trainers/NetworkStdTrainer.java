package org.project.neural.process.training.trainers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.model.Indicators;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.training.NetworkTrainer;
import org.project.neural.process.training.dataset.DatasetProviders;
import org.project.neural.process.training.training.TrainParams;

@Slf4j
@RequiredArgsConstructor
public class NetworkStdTrainer extends AbstractTrainer implements NetworkTrainer {

    private final DatasetProviders datasetProviders;
    private final NetworkStore networkStore;

    @Override
    public void train(TrainParams params) {
        var symbol = params.getSymbol();
        log.info("Start training STD network for {}", symbol);
        var stocks = params.getStocks();
        var datasetProvider = datasetProviders.get(Indicators.STD);
        var network = networkStore.get(Indicators.STD, symbol);
        var dataset = datasetProvider.getData(symbol, stocks);
        var answers = datasetProvider.extractAnswers(dataset);
        var epochs = params.getEpochs();
        removeLastElement(dataset);
        network.train(dataset, answers, epochs);
        networkStore.updateNetwork(Indicators.STD, symbol, network);
    }
}
