package org.project.neural.process.training.trainers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.data.services.interfaces.indicators.ExponentialMovingAverageService;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.training.NetworkTrainer;
import org.project.neural.process.training.training.TrainParams;

@Slf4j
@RequiredArgsConstructor
public class NetworkEmaTrainer implements NetworkTrainer {

    private final ExponentialMovingAverageService exponentialMovingAverageService;
    private final NetworkStore networkStore;

    @Override
    public void train(TrainParams params) {
        var symbol = params.getSymbol();
        log.info("Start training EMA network for {}", symbol);
    }
}
