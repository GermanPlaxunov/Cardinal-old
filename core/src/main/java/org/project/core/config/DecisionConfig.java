package org.project.core.config;

import org.libra.data.config.DataBeansConfig;
import org.libra.data.services.interfaces.AccountService;
import org.libra.data.services.interfaces.ProcessParamsService;
import org.project.core.core.process.decision.BuyAmountCurrencyProcessor;
import org.project.core.core.process.decision.DecisionStarter;
import org.project.core.core.process.decision.indicators.*;
import org.project.neural.process.NeuralProcessStarter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DataBeansConfig.class})
@ComponentScan(basePackages = {
        "org.project.data.config"
})
public class DecisionConfig {

    @Bean
    public DecisionStarter decisionStarter(BuyAmountCurrencyProcessor buyAmountCurrencyProcessor,
                                           IndicatorProcessorsStore indicatorProcessorsStore) {
        return new DecisionStarter(buyAmountCurrencyProcessor,
                indicatorProcessorsStore);
    }

    @Bean
    public BuyAmountCurrencyProcessor buyAmountCurrencyProcessor(ProcessParamsService processParamsService,
                                                                 AccountService accountService) {
        return new BuyAmountCurrencyProcessor(processParamsService,
                accountService);
    }

    @Bean
    public ApoProcessor apoProcessor(NeuralProcessStarter neuralProcessStarter) {
        return new ApoProcessor(neuralProcessStarter);
    }

    @Bean
    public BbandProcessor bbandProcessor(NeuralProcessStarter neuralProcessStarter) {
        return new BbandProcessor(neuralProcessStarter);
    }

    @Bean
    public EmaProcessor emaProcessor(NeuralProcessStarter neuralProcessStarter) {
        return new EmaProcessor(neuralProcessStarter);
    }

    @Bean
    public RsiProcessor rsiProcessor(NeuralProcessStarter neuralProcessStarter) {
        return new RsiProcessor(neuralProcessStarter);
    }

    @Bean
    public SmaProcessor smaProcessor(NeuralProcessStarter neuralProcessStarter) {
        return new SmaProcessor(neuralProcessStarter);
    }

    @Bean
    public StdProcessor stdProcessor(NeuralProcessStarter neuralProcessStarter) {
        return new StdProcessor(neuralProcessStarter);
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
