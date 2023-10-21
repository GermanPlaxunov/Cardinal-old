package org.project.neural.config;

import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.LastProvidedStockService;
import org.project.data.services.interfaces.NeuralPredictionService;
import org.project.data.services.interfaces.ProcessParamsService;
import org.project.data.services.interfaces.indicators.*;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.predictions.PredictorsStore;
import org.project.neural.process.predictions.predictors.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PredictionConfig {

    @Bean
    public PredictorsStore predictorsStore(ApoPredictor apoPredictor,
                                           BbandPredictor bbandPredictor,
                                           EmaPredictor emaPredictor,
                                           RsiPredictor rsiPredictor,
                                           SmaPredictor smaPredictor,
                                           StdPredictor stdPredictor) {
        return new PredictorsStore(apoPredictor,
                bbandPredictor,
                emaPredictor,
                rsiPredictor,
                smaPredictor,
                stdPredictor);
    }

    @Bean
    public ApoPredictor apoPredictor(AbsolutePriceOscillatorService absolutePriceOscillatorService,
                                     LastProvidedStockService lastProvidedStockService,
                                     NeuralPredictionService neuralPredictionService,
                                     ProcessParamsService processParamsService,
                                     CoreStockService coreStockService,
                                     NetworkStore networkStore) {
        return new ApoPredictor(absolutePriceOscillatorService,
                lastProvidedStockService,
                neuralPredictionService,
                processParamsService,
                coreStockService,
                networkStore);
    }

    @Bean
    public BbandPredictor bbandPredictor(LastProvidedStockService lastProvidedStockService,
                                         NeuralPredictionService neuralPredictionService,
                                         BollingerBandsService bollingerBandsService,
                                         ProcessParamsService processParamsService,
                                         CoreStockService coreStockService,
                                         NetworkStore networkStore) {
        return new BbandPredictor(lastProvidedStockService,
                neuralPredictionService,
                bollingerBandsService,
                processParamsService,
                coreStockService,
                networkStore);
    }

    @Bean
    public EmaPredictor emaPredictor(ExponentialMovingAverageService exponentialMovingAverageService,
                                     LastProvidedStockService lastProvidedStockService,
                                     NeuralPredictionService neuralPredictionService,
                                     ProcessParamsService processParamsService,
                                     CoreStockService coreStockService,
                                     NetworkStore networkStore) {
        return new EmaPredictor(exponentialMovingAverageService,
                lastProvidedStockService,
                neuralPredictionService,
                processParamsService,
                coreStockService,
                networkStore);
    }

    @Bean
    public RsiPredictor rsiPredictor(RelativeStrengthIndicatorService relativeStrengthIndicatorService,
                                     LastProvidedStockService lastProvidedStockService,
                                     NeuralPredictionService neuralPredictionService,
                                     ProcessParamsService processParamsService,
                                     CoreStockService coreStockService,
                                     NetworkStore networkStore) {
        return new RsiPredictor(relativeStrengthIndicatorService,
                lastProvidedStockService,
                neuralPredictionService,
                processParamsService,
                coreStockService,
                networkStore);
    }

    @Bean
    public SmaPredictor smaPredictor(SimpleMovingAverageService simpleMovingAverageService,
                                     LastProvidedStockService lastProvidedStockService,
                                     NeuralPredictionService neuralPredictionService,
                                     ProcessParamsService processParamsService,
                                     CoreStockService coreStockService,
                                     NetworkStore networkStore) {
        return new SmaPredictor(simpleMovingAverageService,
                lastProvidedStockService,
                neuralPredictionService,
                processParamsService,
                coreStockService,
                networkStore);
    }

    @Bean
    public StdPredictor stdPredictor(StandardDerivativesService standardDerivativesService,
                                     LastProvidedStockService lastProvidedStockService,
                                     NeuralPredictionService neuralPredictionService,
                                     ProcessParamsService processParamsService,
                                     CoreStockService coreStockService,
                                     NetworkStore networkStore) {
        return new StdPredictor(standardDerivativesService,
                lastProvidedStockService,
                neuralPredictionService,
                processParamsService,
                coreStockService,
                networkStore);
    }

}
