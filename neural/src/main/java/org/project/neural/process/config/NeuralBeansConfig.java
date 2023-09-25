package org.project.neural.process.config;

import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.indicators.RelativeStrengthIndicatorService;
import org.project.data.services.interfaces.neural.NeuralNetworkService;
import org.project.neural.process.NeuralProcessStarter;
import org.project.neural.process.network.NetworkDao;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.predictions.PredictorsStore;
import org.project.neural.process.training.NetworkVectorProcessor;
import org.project.neural.process.training.TrainParamsProvider;
import org.project.neural.process.training.TrainersStore;
import org.project.neural.process.training.trainers.NetworkRsiTrainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NeuralBeansConfig {

    @Bean
    public NetworkStore networkStore(NetworkVectorProcessor networkVectorProcessor,
                                     NeuralNetworkService neuralNetworkService,
                                     NetworkDao networkDao) {
        return new NetworkStore(networkVectorProcessor,
                neuralNetworkService,
                networkDao);
    }

    @Bean
    public NetworkVectorProcessor networkVectorProcessor() {
        return new NetworkVectorProcessor();
    }

    @Bean
    public NeuralProcessStarter neuralProcessStarter(TrainParamsProvider trainParamsProvider,
                                                     PredictorsStore predictorsStore,
                                                     TrainersStore trainersStore) {
        return new NeuralProcessStarter(trainParamsProvider,
                predictorsStore,
                trainersStore);
    }

    @Bean
    public TrainParamsProvider trainParamsProvider(CoreStockService coreStockService) {
        return new TrainParamsProvider(coreStockService);
    }

    @Bean
    public NetworkDao networkDao(NetworkVectorProcessor networkVectorProcessor,
                                 NeuralNetworkService neuralNetworkService) {
        return new NetworkDao(networkVectorProcessor,
                neuralNetworkService);
    }
}
