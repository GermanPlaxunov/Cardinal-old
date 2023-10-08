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
public class NetworkBbandTrainer extends AbstractTrainer implements NetworkTrainer {

    private final DatasetProviders datasetProviders;
    private final NetworkStore networkStore;

    @Override
    public void train(TrainParams params) {
        var symbol = params.getSymbol();
        log.info("Start training BBAND network for {}", symbol);
        var stocks = params.getStocks();
        var datasetProvider = datasetProviders.get(Indicators.BBAND);
        var network = networkStore.get(Indicators.BBAND, symbol);
        var dataset = datasetProvider.getData(symbol, stocks);
        var answers = extractAnswers(dataset);
        var epochs = params.getEpochs();
        removeLastElement(dataset);
        network.train(dataset, answers, epochs);
        networkStore.updateNetwork(Indicators.BBAND, symbol, network);
    }
}
