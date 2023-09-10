package org.project.neural.process.config;

import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.indicators.RelativeStrengthIndicatorService;
import org.project.data.services.interfaces.neural.NeuralNetworkService;
import org.project.neural.process.NeuralProcessStarter;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.predictions.RsiPredictor;
import org.project.neural.process.training.NetworkRsiTrainer;
import org.project.neural.process.training.NetworkVectorProcessor;
import org.project.neural.process.training.TrainParamsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NeuralBeansConfig {

    @Bean
    public NetworkStore networkStore(NetworkVectorProcessor networkVectorProcessor,
                                     NeuralNetworkService neuralNetworkService) {
        return new NetworkStore(networkVectorProcessor,
                neuralNetworkService);
    }

    @Bean
    public NetworkVectorProcessor networkVectorProcessor() {
        return new NetworkVectorProcessor();
    }

    @Bean
    public NetworkRsiTrainer networkRsiTrainer(
            RelativeStrengthIndicatorService relativeStrengthIndicatorService,
            NetworkStore networkStore) {
        return new NetworkRsiTrainer(relativeStrengthIndicatorService,
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
