package org.project.neural.process.training.trainers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.model.Indicators;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.training.NetworkTrainer;
import org.project.neural.process.training.dataset.DatasetProviders;
import org.project.neural.process.training.training.TrainParams;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class NetworkApoTrainer implements NetworkTrainer {

    private final DatasetProviders datasetProviders;
    private final NetworkStore networkStore;

    @Override
    public void train(TrainParams params) {
        var symbol = params.getSymbol();
        log.info("Start training APO network for {}", symbol);
    }
}
