package org.project.neural.process.training.trainers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.data.services.interfaces.indicators.BollingerBandsService;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.training.NetworkTrainer;
import org.project.neural.process.training.training.TrainParams;

@Slf4j
@RequiredArgsConstructor
public class NetworkBbandTrainer implements NetworkTrainer {

    private final BollingerBandsService bollingerBandsService;
    private final NetworkStore networkStore;

    @Override
    public void train(TrainParams params) {
        var symbol = params.getSymbol();
        log.info("Start training BBAND network for {}", symbol);
    }
}
