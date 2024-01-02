package org.cardinal.core.config;

import org.cardinal.core.commission.CommissionProcessor;
import org.cardinal.core.deal.DealMaker;
import org.cardinal.core.indicators.IndicatorsCollector;
import org.cardinal.core.indicators.IndicatorsPredictionsCollector;
import org.cardinal.core.indicators.IndicatorsSaver;
import org.cardinal.core.mapper.CoreStockMapper;
import org.cardinal.core.mapper.CoreStockMapperImpl;
import org.cardinal.core.market.MarketDataProvider;
import org.cardinal.core.process.CoreProcessStarter;
import org.cardinal.core.strategy.MainStrategy;
import org.cardinal.core.trend.AveragePriceTrendProvider;
import org.cardinal.core.trend.StocksDivider;
import org.cardinal.core.trend.TrendProvider;
import org.cardinal.data.cache.CacheDepthMapper;
import org.cardinal.data.cache.CacheDepthProvider;
import org.cardinal.data.cache.CacheDepthProviderImpl;
import org.cardinal.data.config.DataBeansConfig;
import org.cardinal.data.services.interfaces.AccountService;
import org.cardinal.data.services.interfaces.CoreStockService;
import org.cardinal.data.services.interfaces.PositionService;
import org.cardinal.data.services.interfaces.ProcessParamsService;
import org.cardinal.data.services.interfaces.indicators.*;
import org.cardinal.decision.config.DecisionConfig;
import org.cardinal.decision.processor.indicators.IndicatorProcessorsStore;
import org.cardinal.indicators.config.IndicatorsConfig;
import org.cardinal.indicators.indicators.*;
import org.cardinal.neural.config.NeuralBeansConfig;
import org.cardinal.neural.process.NeuralProcessStarter;
import org.cardinal.core.decision.BuyAmountCurrencyProcessor;
import org.cardinal.core.decision.DecisionStarter;
import org.project.market.config.MarketConfig;
import org.project.market.process.MarketService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DataBeansConfig.class, IndicatorsConfig.class,
        DecisionConfig.class, MarketConfig.class,
        NeuralBeansConfig.class})
@EntityScan(basePackages = "org.cardinal.data.entities")
public class CoreBeansConfig {

    @Bean
    public CoreStockMapper coreStockMapper() {
        return new CoreStockMapperImpl();
    }

    @Bean
    public IndicatorsPredictionsCollector indicatorsPredictionsCollector(
            NeuralProcessStarter neuralProcessStarter) {
        return new IndicatorsPredictionsCollector(neuralProcessStarter);
    }

    @Bean
    public DealMaker dealMaker(MarketService marketService) {
        return new DealMaker(marketService);
    }

    @Bean
    public CommissionProcessor commissionProcessor(ProcessParamsService processParamsService) {
        return new CommissionProcessor(processParamsService);
    }

    @Bean
    public CoreProcessStarter coreProcessStarter(MarketDataProvider marketDataProvider,
                                                 CoreStockService coreStockService,
                                                 MainStrategy mainStrategy) {
        return new CoreProcessStarter(marketDataProvider,
                coreStockService,
                mainStrategy);
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
                                                   org.cardinal.indicators.indicators.BollingerBands bollingerBands) {
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
    public org.project.market.mapper.StockMapper marketStockMapper() {
        return new org.project.market.mapper.StockMapperImpl();
    }

    @Bean
    public MarketDataProvider marketDataProvider(CoreStockService coreStockService,
                                                 MarketService marketService,
                                                 org.project.market.mapper.StockMapper marketStockMapper,
                                                 CoreStockMapper coreStockMapper) {
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
    public MainStrategy mainStrategy(PositionService positionService) {
        return new MainStrategy(positionService);
    }

    @Bean
    public DecisionStarter decisionStarter(BuyAmountCurrencyProcessor buyAmountCurrencyProcessor,
                                           IndicatorProcessorsStore indicatorProcessorsStore) {
        return new DecisionStarter(buyAmountCurrencyProcessor,
                indicatorProcessorsStore);
    }

    @Bean
    public BuyAmountCurrencyProcessor buyAmountCurrencyProcessor(ProcessParamsService processParamsService,
                                                                 AccountService accountService) {
        return new BuyAmountCurrencyProcessor(processParamsService,
                accountService);
    }

}
