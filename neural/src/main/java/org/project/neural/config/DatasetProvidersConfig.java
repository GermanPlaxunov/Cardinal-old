package org.project.neural.config;

import org.libra.data.services.interfaces.ProcessParamsService;
import org.libra.data.services.interfaces.indicators.*;
import org.project.neural.process.training.dataset.*;
import org.project.neural.process.training.dataset.delta.PriceChangeCalculator;
import org.project.neural.process.training.dataset.splitters.DataDateSplitter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatasetProvidersConfig {

    @Bean
    public DatasetProviders datasetProviders(ApoDatasetProvider apoDatasetProvider,
                                             BbandDatasetProvider bbandDatasetProvider,
                                             EmaDatasetProvider emaDatasetProvider,
                                             RsiDatasetProvider rsiDatasetProvider,
                                             SmaDatasetProvider smaDatasetProvider,
                                             StdDatasetProvider stdDatasetProvider) {
        return new DatasetProviders(apoDatasetProvider,
                bbandDatasetProvider,
                emaDatasetProvider,
                rsiDatasetProvider,
                smaDatasetProvider,
                stdDatasetProvider);
    }

    @Bean
    public ApoDatasetProvider apoDatasetProvider(
            AbsolutePriceOscillatorService absolutePriceOscillatorService,
            PriceChangeCalculator priceChangeCalculator,
            ProcessParamsService processParamsService,
            DataDateSplitter dataDateSplitter) {
        return new ApoDatasetProvider(absolutePriceOscillatorService,
                priceChangeCalculator,
                processParamsService,
                dataDateSplitter);
    }

    @Bean
    public BbandDatasetProvider bbandDatasetProvider(
            BollingerBandsService bollingerBandsService,
            PriceChangeCalculator priceChangeCalculator,
            ProcessParamsService processParamsService,
            DataDateSplitter dataDateSplitter) {
        return new BbandDatasetProvider(bollingerBandsService,
                priceChangeCalculator,
                processParamsService,
                dataDateSplitter);
    }

    @Bean
    public EmaDatasetProvider emaDatasetProvider(
            ExponentialMovingAverageService exponentialMovingAverageService,
            PriceChangeCalculator priceChangeCalculator,
            ProcessParamsService processParamsService,
            DataDateSplitter dataDateSplitter) {
        return new EmaDatasetProvider(exponentialMovingAverageService,
                priceChangeCalculator,
                processParamsService,
                dataDateSplitter);
    }

    @Bean
    public RsiDatasetProvider rsiDatasetProvider(
            RelativeStrengthIndicatorService relativeStrengthIndicatorService,
            PriceChangeCalculator priceChangeCalculator,
            ProcessParamsService processParamsService,
            DataDateSplitter dataDateSplitter) {
        return new RsiDatasetProvider(relativeStrengthIndicatorService,
                priceChangeCalculator,
                processParamsService,
                dataDateSplitter);
    }

    @Bean
    public SmaDatasetProvider smaDatasetProvider(
            SimpleMovingAverageService simpleMovingAverageService,
            PriceChangeCalculator priceChangeCalculator,
            ProcessParamsService processParamsService,
            DataDateSplitter dataDateSplitter) {
        return new SmaDatasetProvider(simpleMovingAverageService,
                priceChangeCalculator,
                processParamsService,
                dataDateSplitter);
    }

    @Bean
    public StdDatasetProvider stdDatasetProvider(
            StandardDerivativesService standardDerivativesService,
            PriceChangeCalculator priceChangeCalculator,
            ProcessParamsService processParamsService,
            DataDateSplitter dataDateSplitter) {
        return new StdDatasetProvider(standardDerivativesService,
                priceChangeCalculator,
                processParamsService,
                dataDateSplitter);
    }

    @Bean
    public PriceChangeCalculator priceChangeCalculator() {
        return new PriceChangeCalculator();
    }

}
