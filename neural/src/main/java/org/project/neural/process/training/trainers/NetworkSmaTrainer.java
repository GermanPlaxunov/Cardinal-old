package org.project.neural.process.training.trainers;

import lombok.RequiredArgsConstructor;
import org.project.data.services.interfaces.indicators.SimpleMovingAverageService;
import org.project.model.neural.training.TrainParams;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.training.NetworkTrainer;

@RequiredArgsConstructor
public class NetworkSmaTrainer implements NetworkTrainer {

    private final SimpleMovingAverageService simpleMovingAverageService;
    private final NetworkStore networkStore;

    @Override
    public void train(TrainParams params) {

    }
}
