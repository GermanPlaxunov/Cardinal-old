package org.project.neural.process.training.trainers;

import lombok.RequiredArgsConstructor;
import org.project.data.services.interfaces.indicators.ExponentialMovingAverageService;
import org.project.model.neural.training.TrainParams;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.training.NetworkTrainer;

@RequiredArgsConstructor
public class NetworkEmaTrainer implements NetworkTrainer {

    private final ExponentialMovingAverageService exponentialMovingAverageService;
    private final NetworkStore networkStore;

    @Override
    public void train(TrainParams params) {

    }
}
