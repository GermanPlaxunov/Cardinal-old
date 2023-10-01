package org.project.neural.process.training.trainers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.data.services.interfaces.indicators.AbsolutePriceOscillatorService;
import org.project.model.neural.training.TrainParams;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.training.NetworkTrainer;

@Slf4j
@RequiredArgsConstructor
public class NetworkApoTrainer implements NetworkTrainer {

    private final AbsolutePriceOscillatorService absolutePriceOscillatorService;
    private final NetworkStore networkStore;

    @Override
    public void train(TrainParams params) {
        var symbol = params.getSymbol();
        log.info("Start training APO network for {}", symbol);

    }
}
