package org.project.core.config;

import org.project.core.client.MarketClient;
import org.project.core.client.MarketFeignClient;
import org.project.core.client.NeuralFeignClient;
import org.project.core.core.market.MarketDataProvider;
import org.project.core.core.process.ProcessStarter;
import org.project.core.core.process.deal.DealMaker;
import org.project.core.core.process.decision.DecisionMakingCenter;
import org.project.core.core.process.decision.indicators.DecisionProcessorsStore;
import org.project.core.core.process.indicators.*;
import org.project.core.core.process.strategy.MainStrategy;
import org.project.core.mapper.StockMapper;
import org.project.data.cache.CacheDepthMapper;
import org.project.data.cache.CacheDepthProvider;
import org.project.data.cache.CacheDepthProviderImpl;
import org.project.data.config.DataBeansConfig;
import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.PositionService;
import org.project.data.services.interfaces.ProcessParamsService;
import org.project.data.services.interfaces.indicators.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DataBeansConfig.class)
@EnableFeignClients(clients = {
        MarketFeignClient.class, NeuralFeignClient.class
})
@EntityScan(basePackages = "org.project.data.entities")
public class CoreBeansConfig {

    @Bean
    public DealMaker dealMaker(PositionService positionService,
                               MarketClient marketClient) {
        return new DealMaker(positionService,
                marketClient);
    }

    @Bean
    public DecisionMakingCenter decisionMakingCenter(DecisionProcessorsStore decisionProcessorsStore,
                                                     PositionService positionService,
                                                     DealMaker dealMaker) {
        return new DecisionMakingCenter(decisionProcessorsStore,
                positionService,
                dealMaker);
    }

    @Bean
    public ProcessStarter processStarter(DecisionMakingCenter decisionMakingCenter,
                                         IndicatorsCollector indicatorsCollector,
                                         MarketDataProvider marketDataProvider,
                                         CoreStockService coreStockService) {
        return new ProcessStarter(decisionMakingCenter,
                indicatorsCollector,
                marketDataProvider,
                coreStockService);
    }

    @Bean
    public IndicatorsCollector indicatorsCollector(RelativeStrengthIndicator relativeStrengthIndicator,
                                                   ExponentialMovingAverage exponentialMovingAverage,
                                                   AbsolutePriceOscillator absolutePriceOscillator,
                                                   SimpleMovingAverage simpleMovingAverage,
                                                   StandardDerivatives standardDerivatives,
                                                   CacheDepthProvider cacheDepthProvider,
                                                   CoreStockService coreStockService,
                                                   IndicatorsSaver indicatorsSaver,
                                                   BollingerBands bollingerBands) {
        return new IndicatorsCollector(relativeStrengthIndicator,
                exponentialMovingAverage,
                absolutePriceOscillator,
                simpleMovingAverage,
                standardDerivatives,
                cacheDepthProvider,
                coreStockService,
                indicatorsSaver,
                bollingerBands);
    }


    @Bean
    public IndicatorsSaver indicatorsSaver(
            AbsolutePriceOscillatorService absolutePriceOscillatorService,
            BollingerBandsService bollingerBandsService,
            ExponentialMovingAverageService exponentialMovingAverageService,
            RelativeStrengthIndicatorService relativeStrengthIndicatorService,
            SimpleMovingAverageService simpleMovingAverageService,
            StandardDerivativesService standardDerivativesService) {
        return new IndicatorsSaver(absolutePriceOscillatorService,
                bollingerBandsService,
                exponentialMovingAverageService,
                relativeStrengthIndicatorService,
                simpleMovingAverageService,
                standardDerivativesService);
    }

    @Bean
    public MarketDataProvider marketDataProvider(CoreStockService coreStockService,
                                                 MarketClient marketClient,
                                                 StockMapper stockMapper) {
        return new MarketDataProvider(coreStockService,
                marketClient,
                stockMapper);
    }

    @Bean
    public CacheDepthMapper cacheDepthMapper() {
        return new CacheDepthMapper();
    }

    @Bean
    public CacheDepthProvider cacheDepthProvider(ProcessParamsService processParamsService,
                                                 CacheDepthMapper cacheDepthMapper,
                                                 CoreStockService coreStockService) {
        return new CacheDepthProviderImpl(processParamsService,
                cacheDepthMapper,
                coreStockService);
    }

    @Bean
    public MainStrategy mainStrategy() {
        return new MainStrategy();
    }

}
