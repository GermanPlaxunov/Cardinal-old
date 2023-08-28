package org.project.neural.process.config;

import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.indicators.RelativeStrengthIndicatorService;
import org.project.neural.process.NeuralProcessStarter;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.predictions.RsiPredictor;
import org.project.neural.process.training.NetworkRsiTrainer;
import org.project.neural.process.training.TrainParamsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NeuralBeansConfig {

    @Bean
    public NetworkStore networkStore() {
        return new NetworkStore();
    }

    @Bean
    public NetworkRsiTrainer networkRsiTrainer(
            RelativeStrengthIndicatorService relativeStrengthIndicatorService,
            CoreStockService coreStockService,
            NetworkStore networkStore) {
        return new NetworkRsiTrainer(relativeStrengthIndicatorService,
                coreStockService,
                networkStore);
    }

    @Bean
    public RsiPredictor rsiPredictor(RelativeStrengthIndicatorService relativeStrengthIndicatorService,
                                     NetworkStore networkStore) {
        return new RsiPredictor(relativeStrengthIndicatorService,
                networkStore);
    }

    @Bean
    public NeuralProcessStarter neuralProcessStarter(TrainParamsProvider trainParamsProvider,
                                                     NetworkRsiTrainer networkRsiTrainer,
                                                     RsiPredictor rsiPredictor) {
        return new NeuralProcessStarter(trainParamsProvider,
                networkRsiTrainer,
                rsiPredictor);
    }

    @Bean
    public TrainParamsProvider trainParamsProvider(CoreStockService coreStockService) {
        return new TrainParamsProvider(coreStockService);
    }

}
