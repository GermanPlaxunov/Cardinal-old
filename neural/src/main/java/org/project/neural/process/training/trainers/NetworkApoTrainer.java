package org.project.neural.process.training.trainers;

import lombok.RequiredArgsConstructor;
import org.project.data.services.interfaces.indicators.AbsolutePriceOscillatorService;
import org.project.model.neural.training.TrainParams;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.training.NetworkTrainer;

@RequiredArgsConstructor
public class NetworkApoTrainer implements NetworkTrainer {

    private final AbsolutePriceOscillatorService absolutePriceOscillatorService;
    private final NetworkStore networkStore;

    @Override
    public void train(TrainParams params) {

    }
}
