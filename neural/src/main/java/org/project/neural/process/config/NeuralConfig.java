package org.project.neural.process.config;

import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.indicators.RelativeStrengthIndicatorService;
import org.project.neural.process.network.NetworkProvider;
import org.project.neural.process.training.NetworkRsiTrainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NeuralConfig {

    @Bean
    public NetworkProvider networkProvider() {
        return new NetworkProvider();
    }

    @Bean
    public NetworkRsiTrainer networkRsiTrainer(
            RelativeStrengthIndicatorService relativeStrengthIndicatorService,
            CoreStockService coreStockService,
            NetworkProvider networkProvider) {
        return new NetworkRsiTrainer(relativeStrengthIndicatorService,
                coreStockService,
                networkProvider);
    }

}
