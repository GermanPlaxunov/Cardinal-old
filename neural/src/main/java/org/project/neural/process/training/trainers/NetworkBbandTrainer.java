package org.project.neural.process.training.trainers;

import lombok.RequiredArgsConstructor;
import org.project.data.services.interfaces.indicators.BollingerBandsService;
import org.project.model.neural.training.TrainParams;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.training.NetworkTrainer;

@RequiredArgsConstructor
public class NetworkBbandTrainer implements NetworkTrainer {

    private final BollingerBandsService bollingerBandsService;
    private final NetworkStore networkStore;

    @Override
    public void train(TrainParams params) {

    }
}
