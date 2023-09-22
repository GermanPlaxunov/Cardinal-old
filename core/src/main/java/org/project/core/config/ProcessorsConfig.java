package org.project.core.config;

import org.project.core.client.NeuralClient;
import org.project.core.core.process.decision.indicators.DecisionProcessorsStore;
import org.project.core.core.process.decision.indicators.apo.ApoDecisionProcessor;
import org.project.core.core.process.decision.indicators.apo.ApoPredictionProcessor;
import org.project.core.core.process.decision.indicators.bband.BbandDecisionProcessor;
import org.project.core.core.process.decision.indicators.bband.BbandPredictionProcessor;
import org.project.core.core.process.decision.indicators.ema.EmaDecisionProcessor;
import org.project.core.core.process.decision.indicators.ema.EmaPredictionProcessor;
import org.project.core.core.process.decision.indicators.rsi.RsiDecisionProcessor;
import org.project.core.core.process.decision.indicators.rsi.RsiPredictionProcessor;
import org.project.core.core.process.decision.indicators.sma.SmaDecisionProcessor;
import org.project.core.core.process.decision.indicators.sma.SmaPredictionProcessor;
import org.project.core.core.process.decision.indicators.std.StdDerivsDecisionProcessor;
import org.project.core.core.process.decision.indicators.std.StdPredictionProcessor;
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
    public RsiDecisionProcessor rsiDecisionProcessor(RsiPredictionProcessor rsiPredictionProcessor,
                                                     NeuralClient neuralClient) {
        return new RsiDecisionProcessor(rsiPredictionProcessor,
                neuralClient);
    }

    @Bean
    public RsiPredictionProcessor rsiPredictionProcessor() {
        return new RsiPredictionProcessor();
    }

    @Bean
    public ApoDecisionProcessor apoDecisionProcessor(ApoPredictionProcessor apoPredictionProcessor,
                                                     NeuralClient neuralClient) {
        return new ApoDecisionProcessor(apoPredictionProcessor,
                neuralClient);
    }

    @Bean
    public BbandDecisionProcessor bbandDecisionProcessor(BbandPredictionProcessor bbandPredictionProcessor,
                                                         NeuralClient neuralClient) {
        return new BbandDecisionProcessor(bbandPredictionProcessor,
                neuralClient);
    }

    @Bean
    public EmaDecisionProcessor emaDecisionProcessor(EmaPredictionProcessor emaPredictionProcessor,
                                                     NeuralClient neuralClient) {
        return new EmaDecisionProcessor(emaPredictionProcessor,
                neuralClient);
    }

    @Bean
    public SmaDecisionProcessor smaDecisionProcessor(SmaPredictionProcessor smaPredictionProcessor,
                                                     NeuralClient neuralClient) {
        return new SmaDecisionProcessor(smaPredictionProcessor,
                neuralClient);
    }

    @Bean
    public StdDerivsDecisionProcessor stdDerivsDecisionProcessor(StdPredictionProcessor stdPredictionProcessor,
                                                                 NeuralClient neuralClient) {
        return new StdDerivsDecisionProcessor(stdPredictionProcessor,
                neuralClient);
    }

    @Bean
    public ApoPredictionProcessor apoPredictionProcessor() {
        return new ApoPredictionProcessor();
    }

    @Bean
    public BbandPredictionProcessor bbandPredictionProcessor() {
        return new BbandPredictionProcessor();
    }

    @Bean
    public EmaPredictionProcessor emaPredictionProcessor() {
        return new EmaPredictionProcessor();
    }

    @Bean
    public SmaPredictionProcessor smaPredictionProcessor() {
        return new SmaPredictionProcessor();
    }

    @Bean
    public StdPredictionProcessor stdPredictionProcessor() {
        return new StdPredictionProcessor();
    }
}
