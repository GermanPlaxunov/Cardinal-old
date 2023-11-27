package org.project.core.config;

import org.project.core.core.process.decision.DecisionStarter;
import org.project.core.core.process.decision.indicators.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DecisionConfig {

    @Bean
    public DecisionStarter decisionStarter() {
        return new DecisionStarter();
    }

    @Bean
    public ApoProcessor apoProcessor() {
        return new ApoProcessor();
    }

    @Bean
    public BbandProcessor bbandProcessor() {
        return new BbandProcessor();
    }

    @Bean
    public EmaProcessor emaProcessor() {
        return new EmaProcessor();
    }

    @Bean
    public RsiProcessor rsiProcessor() {
        return new RsiProcessor();
    }

    @Bean
    public SmaProcessor smaProcessor() {
        return new SmaProcessor();
    }

    @Bean
    public StdProcessor stdProcessor() {
        return new StdProcessor();
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
