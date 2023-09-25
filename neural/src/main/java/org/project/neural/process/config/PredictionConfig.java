package org.project.neural.process.config;

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
                                     NetworkStore networkStore) {
        return new ApoPredictor(absolutePriceOscillatorService,
                networkStore);
    }

    @Bean
    public BbandPredictor bbandPredictor(BollingerBandsService bollingerBandsService,
                                         NetworkStore networkStore) {
        return new BbandPredictor(bollingerBandsService,
                networkStore);
    }

    @Bean
    public EmaPredictor emaPredictor(ExponentialMovingAverageService exponentialMovingAverageService,
                                     NetworkStore networkStore) {
        return new EmaPredictor(exponentialMovingAverageService,
                networkStore);
    }

    @Bean
    public RsiPredictor rsiPredictor(RelativeStrengthIndicatorService relativeStrengthIndicatorService,
                                     NetworkStore networkStore) {
        return new RsiPredictor(relativeStrengthIndicatorService,
                networkStore);
    }

    @Bean
    public SmaPredictor smaPredictor(SimpleMovingAverageService simpleMovingAverageService,
                                     NetworkStore networkStore) {
        return new SmaPredictor(simpleMovingAverageService,
                networkStore);
    }

    @Bean
    public StdPredictor stdPredictor(StandardDerivativesService standardDerivativesService,
                                     NetworkStore networkStore) {
        return new StdPredictor(standardDerivativesService,
                networkStore);
    }

}
