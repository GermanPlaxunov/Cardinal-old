package org.project.core.config;

import org.project.core.client.NeuralClient;
import org.project.core.core.process.decision.DecisionStarter;
import org.project.core.core.process.decision.indicators.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DecisionConfig {

    @Bean
    public DecisionStarter decisionStarter(IndicatorProcessorsStore indicatorProcessorsStore) {
        return new DecisionStarter(indicatorProcessorsStore);
    }

    @Bean
    public ApoProcessor apoProcessor(NeuralClient neuralClient) {
        return new ApoProcessor(neuralClient);
    }

    @Bean
    public BbandProcessor bbandProcessor(NeuralClient neuralClient) {
        return new BbandProcessor(neuralClient);
    }

    @Bean
    public EmaProcessor emaProcessor(NeuralClient neuralClient) {
        return new EmaProcessor(neuralClient);
    }

    @Bean
    public RsiProcessor rsiProcessor(NeuralClient neuralClient) {
        return new RsiProcessor(neuralClient);
    }

    @Bean
    public SmaProcessor smaProcessor(NeuralClient neuralClient) {
        return new SmaProcessor(neuralClient);
    }

    @Bean
    public StdProcessor stdProcessor(NeuralClient neuralClient) {
        return new StdProcessor(neuralClient);
    }

    @Bean
    public IndicatorProcessorsStore indicatorProcessorsStore(ApoProcessor apoProcessor,
                                                             BbandProcessor bbandProcessor,
                                                             EmaProcessor emaProcessor,
                                                             RsiProcessor rsiProcessor,
                                                             SmaProcessor smaProcessor,
                                                             StdProcessor stdProcessor) {
        return new IndicatorProcessorsStore(apoProcessor,
                bbandProcessor,
                emaProcessor,
                rsiProcessor,
                smaProcessor,
                stdProcessor);
    }
}
