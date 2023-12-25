package org.project.core.config;

import org.libra.bragi.services.interfaces.indicators.*;
import org.project.core.client.MarketClient;
import org.project.core.client.MarketFeignClient;
import org.project.core.client.NeuralFeignClient;
import org.project.core.core.market.MarketDataProvider;
import org.project.core.core.process.ProcessStarter;
import org.project.core.core.process.broker.commission.CommissionProcessor;
import org.project.core.core.process.data.trend.AveragePriceTrendProvider;
import org.project.core.core.process.data.trend.StocksDivider;
import org.project.core.core.process.data.trend.TrendProvider;
import org.project.core.core.process.deal.DealMaker;
import org.project.core.core.process.decision.DecisionStarter;
import org.project.core.core.process.indicators.*;
import org.project.core.core.process.strategy.MainStrategy;
import org.project.core.mapper.StockMapper;
import org.libra.bragi.cache.CacheDepthMapper;
import org.libra.bragi.cache.CacheDepthProvider;
import org.libra.bragi.cache.CacheDepthProviderImpl;
import org.libra.bragi.config.BragiBeansConfig;
import org.libra.bragi.services.interfaces.CoreStockService;
import org.libra.bragi.services.interfaces.PositionService;
import org.libra.bragi.services.interfaces.ProcessParamsService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BragiBeansConfig.class)
@EnableFeignClients(clients = {
        MarketFeignClient.class, NeuralFeignClient.class
})
@EntityScan(basePackages = "org.project.data.entities")
public class CoreBeansConfig {

    @Bean
    public DealMaker dealMaker(MarketClient marketClient) {
        return new DealMaker(marketClient);
    }

    @Bean
    public CommissionProcessor commissionProcessor(ProcessParamsService processParamsService) {
        return new CommissionProcessor(processParamsService);
    }

    @Bean
    public ProcessStarter processStarter(ProcessParamsService processParamsService,
                                         CommissionProcessor commissionProcessor,
                                         IndicatorsCollector indicatorsCollector,
                                         MarketDataProvider marketDataProvider,
                                         CoreStockService coreStockService,
                                         PositionService positionService,
                                         TrendProvider trendProvider,
                                         MainStrategy mainStrategy,
                                         StockMapper stockMapper,
                                         DealMaker dealMaker) {
        return new ProcessStarter(processParamsService,
                commissionProcessor,
                indicatorsCollector,
                marketDataProvider,
                coreStockService,
                positionService,
                trendProvider,
                mainStrategy,
                stockMapper,
                dealMaker);
    }

    @Bean
    public TrendProvider trendProvider(ProcessParamsService processParamsService,
                                       StocksDivider stocksDivider) {
        return new AveragePriceTrendProvider(processParamsService,
                stocksDivider);
    }

    @Bean
    public StocksDivider stocksDivider(ProcessParamsService processParamsService) {
        return new StocksDivider(processParamsService);
    }

    @Bean
    public IndicatorsCollector indicatorsCollector(RelativeStrengthIndicator relativeStrengthIndicator,
                                                   ExponentialMovingAverage exponentialMovingAverage,
                                                   AbsolutePriceOscillator absolutePriceOscillator,
                                                   SimpleMovingAverage simpleMovingAverage,
                                                   StandardDerivatives standardDerivatives,
                                                   CacheDepthProvider cacheDepthProvider,
                                                   IndicatorsSaver indicatorsSaver,
                                                   BollingerBands bollingerBands) {
        return new IndicatorsCollector(relativeStrengthIndicator,
                exponentialMovingAverage,
                absolutePriceOscillator,
                simpleMovingAverage,
                standardDerivatives,
                cacheDepthProvider,
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
    public MainStrategy mainStrategy(DecisionStarter decisionStarter) {
        return new MainStrategy(decisionStarter);
    }

}
