package org.project.neural.config;

import org.project.data.entities.indicators.*;
import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.ProcessParamsService;
import org.project.data.services.interfaces.indicators.*;
import org.project.neural.process.training.dataset.*;
import org.project.neural.process.training.dataset.delta.PriceChangeCalculator;
import org.project.neural.process.training.dataset.splitters.CoreStocksSplitter;
import org.project.neural.process.training.dataset.splitters.IndicatorSplitter;
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
            IndicatorSplitter<AbsolutePriceOscillatorEntity> indicatorSplitter,
            AbsolutePriceOscillatorService absolutePriceOscillatorService,
            PriceChangeCalculator priceChangeCalculator,
            ProcessParamsService processParamsService,
            CoreStocksSplitter coreStocksSplitter) {
        return new ApoDatasetProvider(indicatorSplitter,
                absolutePriceOscillatorService,
                priceChangeCalculator,
                processParamsService,
                coreStocksSplitter);
    }

    @Bean
    public BbandDatasetProvider bbandDatasetProvider(
            IndicatorSplitter<BollingerBandsEntity> indicatorSplitter,
            BollingerBandsService bollingerBandsService,
            PriceChangeCalculator priceChangeCalculator,
            ProcessParamsService processParamsService,
            CoreStocksSplitter coreStocksSplitter) {
        return new BbandDatasetProvider(indicatorSplitter,
                bollingerBandsService,
                priceChangeCalculator,
                processParamsService,
                coreStocksSplitter);
    }

    @Bean
    public EmaDatasetProvider emaDatasetProvider(
            IndicatorSplitter<ExponentialMovingAverageEntity> indicatorSplitter,
            ExponentialMovingAverageService exponentialMovingAverageService,
            PriceChangeCalculator priceChangeCalculator,
            ProcessParamsService processParamsService,
            CoreStocksSplitter coreStocksSplitter) {
        return new EmaDatasetProvider(indicatorSplitter,
                exponentialMovingAverageService,
                priceChangeCalculator,
                processParamsService,
                coreStocksSplitter);
    }

    @Bean
    public RsiDatasetProvider rsiDatasetProvider(
            IndicatorSplitter<RelativeStrengthIndicatorEntity> indicatorSplitter,
            RelativeStrengthIndicatorService relativeStrengthIndicatorService,
            PriceChangeCalculator priceChangeCalculator,
            ProcessParamsService processParamsService,
            CoreStocksSplitter coreStocksSplitter) {
        return new RsiDatasetProvider(indicatorSplitter,
                relativeStrengthIndicatorService,
                priceChangeCalculator,
                processParamsService,
                coreStocksSplitter);
    }

    @Bean
    public SmaDatasetProvider smaDatasetProvider(
            IndicatorSplitter<SimpleMovingAverageEntity> indicatorSplitter,
            SimpleMovingAverageService simpleMovingAverageService,
            PriceChangeCalculator priceChangeCalculator,
            ProcessParamsService processParamsService,
            CoreStocksSplitter coreStocksSplitter) {
        return new SmaDatasetProvider(indicatorSplitter,
                simpleMovingAverageService,
                priceChangeCalculator,
                processParamsService,
                coreStocksSplitter);
    }

    @Bean
    public StdDatasetProvider stdDatasetProvider(
            IndicatorSplitter<StandardDerivativesEntity> indicatorSplitter,
            StandardDerivativesService standardDerivativesService,
            PriceChangeCalculator priceChangeCalculator,
            ProcessParamsService processParamsService,
            CoreStocksSplitter coreStocksSplitter) {
        return new StdDatasetProvider(indicatorSplitter,
                standardDerivativesService,
                priceChangeCalculator,
                processParamsService,
                coreStocksSplitter);
    }

    @Bean
    public PriceChangeCalculator priceChangeCalculator() {
        return new PriceChangeCalculator();
    }

}
