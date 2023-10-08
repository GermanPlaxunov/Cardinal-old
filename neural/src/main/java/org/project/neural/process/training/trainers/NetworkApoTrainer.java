package org.project.neural.process.training.trainers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.model.Indicators;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.training.NetworkTrainer;
import org.project.neural.process.training.dataset.ApoDatasetProvider;
import org.project.neural.process.training.training.TrainParams;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class NetworkApoTrainer implements NetworkTrainer {

    private final ApoDatasetProvider apoDatasetProvider;
    private final NetworkStore networkStore;

    @Override
    public void train(TrainParams params) {
        var symbol = params.getSymbol();
        log.info("Start training APO network for {}", symbol);
        var network = networkStore.get(Indicators.APO, symbol);
        var stocks = params.getStocks();
        var data = apoDatasetProvider.getData(symbol, stocks);
        var answers = List.of(1.0); //TODO: Create answer providers
        var epochs = 1000; //TODO: to the paramsProvider
        network.train(data, answers, epochs);
    }
}
