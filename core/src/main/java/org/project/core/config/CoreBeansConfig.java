package org.project.core.config;

import org.libra.data.cache.CacheDepthMapper;
import org.libra.data.cache.CacheDepthProvider;
import org.libra.data.cache.CacheDepthProviderImpl;
import org.libra.data.config.DataBeansConfig;
import org.libra.data.services.interfaces.CoreStockService;
import org.libra.data.services.interfaces.PositionService;
import org.libra.data.services.interfaces.ProcessParamsService;
import org.libra.data.services.interfaces.indicators.*;
import org.libra.indicators.config.IndicatorsConfig;
import org.libra.indicators.indicators.*;
import org.project.core.core.market.MarketDataProvider;
import org.project.core.core.process.IndicatorsCollector;
import org.project.core.core.process.IndicatorsSaver;
import org.project.core.core.process.TradeProcessStarter;
import org.project.core.core.process.broker.commission.CommissionProcessor;
import org.project.core.core.process.data.trend.AveragePriceTrendProvider;
import org.project.core.core.process.data.trend.StocksDivider;
import org.project.core.core.process.data.trend.TrendProvider;
import org.project.core.core.process.deal.DealMaker;
import org.project.core.core.process.decision.DecisionStarter;
import org.project.core.core.process.strategy.MainStrategy;
import org.project.core.mapper.StockMapper;
import org.project.core.mapper.StockMapperImpl;
import org.project.market.config.MarketConfig;
import org.project.market.process.MarketService;
import org.project.model.job.ProcessStarter;
import org.project.neural.config.NeuralBeansConfig;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DataBeansConfig.class, IndicatorsConfig.class,
        DecisionConfig.class, MarketConfig.class, NeuralBeansConfig.class})
@EntityScan(basePackages = "org.project.data.entities")
public class CoreBeansConfig {

    @Bean
    public DealMaker dealMaker(MarketService marketService) {
        return new DealMaker(marketService);
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
        return new TradeProcessStarter(processParamsService,
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
    public StockMapper coreStockMapper() {
        return new StockMapperImpl();
    }

    @Bean
    public org.project.market.mapper.StockMapper marketStockMapper() {
        return new org.project.market.mapper.StockMapperImpl();
    }

    @Bean
    public MarketDataProvider marketDataProvider(CoreStockService coreStockService,
                                                 MarketService marketService,
                                                 org.project.market.mapper.StockMapper marketStockMapper,
                                                 StockMapper coreStockMapper) {
        return new MarketDataProvider(marketService, marketStockMapper,
                coreStockService,
                coreStockMapper);
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
