package org.project.neural.process.config;

import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.training.TrainersStore;
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
    public NetworkApoTrainer networkApoTrainer(DatasetProviders datasetProviders,
                                               NetworkStore networkStore) {
        return new NetworkApoTrainer(datasetProviders,
                networkStore);
    }

    @Bean
    public NetworkBbandTrainer networkBbandTrainer(DatasetProviders datasetProviders,
                                                   NetworkStore networkStore) {
        return new NetworkBbandTrainer(datasetProviders,
                networkStore);
    }

    @Bean
    public NetworkEmaTrainer networkEmaTrainer(DatasetProviders datasetProviders,
                                               NetworkStore networkStore) {
        return new NetworkEmaTrainer(datasetProviders,
                networkStore);
    }

    @Bean
    public NetworkRsiTrainer networkRsiTrainer(DatasetProviders datasetProviders,
                                               NetworkStore networkStore) {
        return new NetworkRsiTrainer(datasetProviders,
                networkStore);
    }

    @Bean
    public NetworkSmaTrainer networkSmaTrainer(DatasetProviders datasetProviders,
                                               NetworkStore networkStore) {
        return new NetworkSmaTrainer(datasetProviders,
                networkStore);
    }

    @Bean
    public NetworkStdTrainer networkStdTrainer(DatasetProviders datasetProviders,
                                               NetworkStore networkStore) {
        return new NetworkStdTrainer(datasetProviders,
                networkStore);
    }

}
