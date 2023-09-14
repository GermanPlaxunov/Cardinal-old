package org.project.core.config;

import org.project.core.client.MarketClient;
import org.project.core.client.MarketFeignClient;
import org.project.core.client.NeuralClient;
import org.project.core.client.NeuralFeignClient;
import org.project.core.core.market.MarketDataProvider;
import org.project.core.core.process.ProcessStarter;
import org.project.core.core.process.deal.DealMaker;
import org.project.core.core.process.decision.DecisionMakingCenter;
import org.project.core.core.process.decision.indicators.DecisionProcessorsStore;
import org.project.core.core.process.decision.indicators.rsi.RsiDecisionProcessor;
import org.project.core.core.process.indicators.*;
import org.project.core.core.process.indicators.cache.BtcCacheDepthProvider;
import org.project.core.core.process.indicators.cache.CacheDepthProvider;
import org.project.core.core.process.strategy.BasicStrategy;
import org.project.core.core.process.strategy.basic.FixProfitProvider;
import org.project.core.core.process.strategy.basic.StopLossProvider;
import org.project.core.mapper.StockMapper;
import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.PositionService;
import org.project.data.services.interfaces.indicators.*;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = {
        MarketFeignClient.class, NeuralFeignClient.class
})
public class CoreBeansConfig {

    @Bean
    public DealMaker dealMaker(PositionService positionService,
                               MarketClient marketClient) {
        return new DealMaker(positionService,
                marketClient);
    }

    @Bean
    public DecisionMakingCenter decisionMakingCenter(RsiDecisionProcessor rsiDecisionProcessor,
                                                     PositionService positionService,
                                                     DealMaker dealMaker) {
        return new DecisionMakingCenter(rsiDecisionProcessor,
                positionService,
                dealMaker);
    }

    @Bean
    public ProcessStarter processStarter(DecisionMakingCenter decisionMakingCenter,
                                         CacheDepthProvider cacheDepthProvider,
                                         IndicatorsCollector indicatorsCollector,
                                         MarketDataProvider marketDataProvider,
                                         BasicStrategy basicStrategy) {
        return new ProcessStarter(decisionMakingCenter,
                cacheDepthProvider,
                indicatorsCollector,
                marketDataProvider,
                basicStrategy);
    }

    @Bean
    public IndicatorsCollector indicatorsCollector(IndicatorsSaver indicatorsSaver,
                                                   AbsolutePriceOscillator absolutePriceOscillator,
                                                   ExponentialMovingAverage exponentialMovingAverage,
                                                   RelativeStrengthIndicator relativeStrengthIndicator,
                                                   SimpleMovingAverage simpleMovingAverage,
                                                   StandardDerivatives standardDerivatives,
                                                   BollingerBands bollingerBands,
                                                   CoreStockService coreStockService) {
        return new IndicatorsCollector(indicatorsSaver,
                absolutePriceOscillator,
                exponentialMovingAverage,
                relativeStrengthIndicator,
                simpleMovingAverage,
                standardDerivatives,
                bollingerBands,
                coreStockService);
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
    public StopLossProvider stopLossProvider() {
        return new StopLossProvider();
    }

    @Bean
    public FixProfitProvider fixProfitProvider() {
        return new FixProfitProvider();
    }

    @Bean
    public BasicStrategy basicStrategy(FixProfitProvider fixProfitProvider,
                                       StopLossProvider stopLossProvider,
                                       PositionService positionService) {
        return new BasicStrategy(fixProfitProvider,
                stopLossProvider,
                positionService);
    }

    @Bean
    public CacheDepthProvider btcCacheDepthProvider(CoreStockService coreStockService) {
        return new BtcCacheDepthProvider(coreStockService);
    }

    @Bean
    public RsiDecisionProcessor rsiDecisionProcessor(PositionService positionService,
                                                     NeuralClient neuralClient) {
        return new RsiDecisionProcessor(positionService,
                neuralClient);
    }

    @Bean
    public DecisionProcessorsStore decisionProcessorsStore(RsiDecisionProcessor rsiDecisionProcessor) {
        return new DecisionProcessorsStore(rsiDecisionProcessor);
    }
}
