package org.project.neural.process.config;

import org.project.data.services.interfaces.indicators.*;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.training.TrainersStore;
import org.project.neural.process.training.dataset.ApoDatasetProvider;
import org.project.neural.process.training.dataset.DatasetProviders;
import org.project.neural.process.training.trainers.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TrainersConfig {

    @Bean
    public TrainersStore trainersStore(NetworkApoTrainer networkApoTrainer,
                                       NetworkBbandTrainer networkBbandTrainer,
                                       NetworkEmaTrainer networkEmaTrainer,
                                       NetworkRsiTrainer networkRsiTrainer,
                                       NetworkSmaTrainer networkSmaTrainer,
                                       NetworkStdTrainer networkStdTrainer) {
        return new TrainersStore(networkApoTrainer,
                networkBbandTrainer,
                networkEmaTrainer,
                networkRsiTrainer,
                networkSmaTrainer,
                networkStdTrainer);
    }

    @Bean
    public NetworkApoTrainer networkApoTrainer(
            DatasetProviders datasetProviders,
            NetworkStore networkStore) {
        return new NetworkApoTrainer(datasetProviders,
                networkStore);
    }

    @Bean
    public NetworkBbandTrainer networkBbandTrainer(
            DatasetProviders datasetProviders,
            NetworkStore networkStore) {
        return new NetworkBbandTrainer(datasetProviders,
                networkStore);
    }

    @Bean
    public NetworkEmaTrainer networkEmaTrainer(
            ExponentialMovingAverageService exponentialMovingAverageService,
            NetworkStore networkStore) {
        return new NetworkEmaTrainer(exponentialMovingAverageService,
                networkStore);
    }

    @Bean
    public NetworkRsiTrainer networkRsiTrainer(
            RelativeStrengthIndicatorService relativeStrengthIndicatorService,
            NetworkStore networkStore) {
        return new NetworkRsiTrainer(relativeStrengthIndicatorService,
                networkStore);
    }

    @Bean
    public NetworkSmaTrainer networkSmaTrainer(
            SimpleMovingAverageService simpleMovingAverageService,
            NetworkStore networkStore) {
        return new NetworkSmaTrainer(simpleMovingAverageService,
                networkStore);
    }

    @Bean
    public NetworkStdTrainer networkStdTrainer(
            StandardDerivativesService standardDerivativesService,
            NetworkStore networkStore) {
        return new NetworkStdTrainer(standardDerivativesService,
                networkStore);
    }

}
