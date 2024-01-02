package org.cardinal.neural.config;

import org.cardinal.data.services.interfaces.CoreStockService;
import org.cardinal.data.services.interfaces.NeuralPredictionService;
import org.cardinal.data.services.interfaces.ProcessParamsService;
import org.cardinal.data.services.interfaces.indicators.*;
import org.cardinal.neural.process.network.NetworkStore;
import org.cardinal.neural.process.predictions.PredictorsStore;
import org.cardinal.neural.process.predictions.predictors.*;
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
                                     NeuralPredictionService neuralPredictionService,
                                     ProcessParamsService processParamsService,
                                     CoreStockService coreStockService,
                                     NetworkStore networkStore) {
        return new ApoPredictor(absolutePriceOscillatorService,
                neuralPredictionService,
                processParamsService,
                coreStockService,
                networkStore);
    }

    @Bean
    public BbandPredictor bbandPredictor(NeuralPredictionService neuralPredictionService,
                                         BollingerBandsService bollingerBandsService,
                                         ProcessParamsService processParamsService,
                                         CoreStockService coreStockService,
                                         NetworkStore networkStore) {
        return new BbandPredictor(neuralPredictionService,
                bollingerBandsService,
                processParamsService,
                coreStockService,
                networkStore);
    }

    @Bean
    public EmaPredictor emaPredictor(ExponentialMovingAverageService exponentialMovingAverageService,
                                     NeuralPredictionService neuralPredictionService,
                                     ProcessParamsService processParamsService,
                                     CoreStockService coreStockService,
                                     NetworkStore networkStore) {
        return new EmaPredictor(exponentialMovingAverageService,
                neuralPredictionService,
                processParamsService,
                coreStockService,
                networkStore);
    }

    @Bean
    public RsiPredictor rsiPredictor(RelativeStrengthIndicatorService relativeStrengthIndicatorService,
                                     NeuralPredictionService neuralPredictionService,
                                     ProcessParamsService processParamsService,
                                     CoreStockService coreStockService,
                                     NetworkStore networkStore) {
        return new RsiPredictor(relativeStrengthIndicatorService,
                neuralPredictionService,
                processParamsService,
                coreStockService,
                networkStore);
    }

    @Bean
    public SmaPredictor smaPredictor(SimpleMovingAverageService simpleMovingAverageService,
                                     NeuralPredictionService neuralPredictionService,
                                     ProcessParamsService processParamsService,
                                     CoreStockService coreStockService,
                                     NetworkStore networkStore) {
        return new SmaPredictor(simpleMovingAverageService,
                neuralPredictionService,
                processParamsService,
                coreStockService,
                networkStore);
    }

    @Bean
    public StdPredictor stdPredictor(StandardDerivativesService standardDerivativesService,
                                     NeuralPredictionService neuralPredictionService,
                                     ProcessParamsService processParamsService,
                                     CoreStockService coreStockService,
                                     NetworkStore networkStore) {
        return new StdPredictor(standardDerivativesService,
                neuralPredictionService,
                processParamsService,
                coreStockService,
                networkStore);
    }

}