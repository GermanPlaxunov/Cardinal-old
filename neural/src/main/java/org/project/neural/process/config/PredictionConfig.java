package org.project.neural.process.config;

import org.project.data.services.interfaces.CoreStockService;
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
                                     ProcessParamsService processParamsService,
                                     CoreStockService coreStockService,
                                     NetworkStore networkStore) {
        return new ApoPredictor(absolutePriceOscillatorService,
                processParamsService,
                coreStockService,
                networkStore);
    }

    @Bean
    public BbandPredictor bbandPredictor(BollingerBandsService bollingerBandsService,
                                         ProcessParamsService processParamsService,
                                         CoreStockService coreStockService,
                                         NetworkStore networkStore) {
        return new BbandPredictor(bollingerBandsService,
                processParamsService,
                coreStockService,
                networkStore);
    }

    @Bean
    public EmaPredictor emaPredictor(ExponentialMovingAverageService exponentialMovingAverageService,
                                     ProcessParamsService processParamsService,
                                     CoreStockService coreStockService,
                                     NetworkStore networkStore) {
        return new EmaPredictor(exponentialMovingAverageService,
                processParamsService,
                coreStockService,
                networkStore);
    }

    @Bean
    public RsiPredictor rsiPredictor(RelativeStrengthIndicatorService relativeStrengthIndicatorService,
                                     ProcessParamsService processParamsService,
                                     CoreStockService coreStockService,
                                     NetworkStore networkStore) {
        return new RsiPredictor(relativeStrengthIndicatorService,
                processParamsService,
                coreStockService,
                networkStore);
    }

    @Bean
    public SmaPredictor smaPredictor(SimpleMovingAverageService simpleMovingAverageService,
                                     ProcessParamsService processParamsService,
                                     CoreStockService coreStockService,
                                     NetworkStore networkStore) {
        return new SmaPredictor(simpleMovingAverageService,
                processParamsService,
                coreStockService,
                networkStore);
    }

    @Bean
    public StdPredictor stdPredictor(StandardDerivativesService standardDerivativesService,
                                     ProcessParamsService processParamsService,
                                     CoreStockService coreStockService,
                                     NetworkStore networkStore) {
        return new StdPredictor(standardDerivativesService,
                processParamsService,
                coreStockService,
                networkStore);
    }

}
