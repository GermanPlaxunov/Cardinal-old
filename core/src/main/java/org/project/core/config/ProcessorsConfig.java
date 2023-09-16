package org.project.core.config;

import org.project.core.client.NeuralClient;
import org.project.core.core.process.decision.indicators.DecisionProcessorsStore;
import org.project.core.core.process.decision.indicators.apo.ApoDecisionProcessor;
import org.project.core.core.process.decision.indicators.bband.BbandDecisionProcessor;
import org.project.core.core.process.decision.indicators.ema.EmaDecisionProcessor;
import org.project.core.core.process.decision.indicators.rsi.RsiDecisionProcessor;
import org.project.core.core.process.decision.indicators.sma.SmaDecisionProcessor;
import org.project.core.core.process.decision.indicators.std.StdDerivsDecisionProcessor;
import org.project.data.services.interfaces.PositionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessorsConfig {

    @Bean
    public DecisionProcessorsStore decisionProcessorsStore(ApoDecisionProcessor apoDecisionProcessor,
                                                           BbandDecisionProcessor bbandDecisionProcessor,
                                                           EmaDecisionProcessor emaDecisionProcessor,
                                                           RsiDecisionProcessor rsiDecisionProcessor,
                                                           SmaDecisionProcessor smaDecisionProcessor,
                                                           StdDerivsDecisionProcessor stdDerivsDecisionProcessor) {
        return new DecisionProcessorsStore(apoDecisionProcessor,
                bbandDecisionProcessor,
                emaDecisionProcessor,
                rsiDecisionProcessor,
                smaDecisionProcessor,
                stdDerivsDecisionProcessor);
    }

    @Bean
    public RsiDecisionProcessor rsiDecisionProcessor(PositionService positionService,
                                                     NeuralClient neuralClient) {
        return new RsiDecisionProcessor(positionService,
                neuralClient);
    }

    @Bean
    public ApoDecisionProcessor apoDecisionProcessor(NeuralClient neuralClient) {
        return new ApoDecisionProcessor(neuralClient);
    }

    @Bean
    public BbandDecisionProcessor bbandDecisionProcessor(NeuralClient neuralClient) {
        return new BbandDecisionProcessor(neuralClient);
    }

    @Bean
    public EmaDecisionProcessor emaDecisionProcessor(NeuralClient neuralClient) {
        return new EmaDecisionProcessor(neuralClient);
    }

    @Bean
    public SmaDecisionProcessor smaDecisionProcessor(NeuralClient neuralClient) {
        return new SmaDecisionProcessor(neuralClient);
    }

    @Bean
    public StdDerivsDecisionProcessor stdDerivsDecisionProcessor(NeuralClient neuralClient) {
        return new StdDerivsDecisionProcessor(neuralClient);
    }
}
