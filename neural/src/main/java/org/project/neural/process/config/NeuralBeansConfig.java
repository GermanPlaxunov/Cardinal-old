package org.project.neural.process.config;

import org.project.data.config.DataBeansConfig;
import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.ProcessParamsService;
import org.project.data.services.interfaces.neural.NeuralNetworkService;
import org.project.neural.process.NeuralProcessStarter;
import org.project.neural.process.network.NetworkDao;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.predictions.PredictorsStore;
import org.project.neural.process.training.NetworkVectorProcessor;
import org.project.neural.process.training.TrainParamsProvider;
import org.project.neural.process.training.TrainersStore;
import org.project.neural.process.training.dataset.DatasetProviders;
import org.project.neural.process.training.dataset.delta.PriceChangeCalculator;
import org.project.neural.process.training.dataset.splitters.CoreStocksSplitter;
import org.project.neural.process.training.verification.NeuralNetworkTesting;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DataBeansConfig.class)
@EntityScan(basePackages = "org.project.data.entities")
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
    public NeuralProcessStarter neuralProcessStarter(NeuralNetworkTesting neuralNetworkTesting,
                                                     TrainParamsProvider trainParamsProvider,
                                                     PredictorsStore predictorsStore,
                                                     TrainersStore trainersStore) {
        return new NeuralProcessStarter(neuralNetworkTesting,
                trainParamsProvider,
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

    @Bean
    public CoreStocksSplitter coreStocksSplitter() {
        return new CoreStocksSplitter();
    }

    @Bean
    public PriceChangeCalculator priceChangeCalculator() {
        return new PriceChangeCalculator();
    }

    @Bean
    public NeuralNetworkTesting neuralNetworkTesting(ProcessParamsService processParamsService,
                                                     CoreStockService coreStockService,
                                                     DatasetProviders datasetProviders,
                                                     NetworkStore networkStore) {
        return new NeuralNetworkTesting(processParamsService,
                coreStockService,
                datasetProviders,
                networkStore);
    }

}
