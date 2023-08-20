package org.project.core.config;

import org.project.core.client.MarketClient;
import org.project.core.client.MarketFeignClient;
import org.project.core.core.market.MarketDataProvider;
import org.project.core.core.process.IndicatorsCollector;
import org.project.core.core.process.ProcessStarter;
import org.project.core.core.process.deal.DealMaker;
import org.project.core.core.process.indicators.*;
import org.project.core.core.process.indicators.cache.BtcCacheDepthProvider;
import org.project.core.core.process.indicators.cache.CacheDepthProvider;
import org.project.core.core.process.strategy.BasicStrategy;
import org.project.core.core.process.strategy.basic.FixProfitProvider;
import org.project.core.core.process.strategy.basic.StopLossProvider;
import org.project.core.mapper.StockMapper;
import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.PositionService;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = {
        MarketFeignClient.class
})
public class CoreBeansConfig {

    @Bean
    public DealMaker dealMaker(MarketClient marketClient) {
        return new DealMaker(marketClient);
    }

    @Bean
    public ProcessStarter processStarter(CacheDepthProvider cacheDepthProvider,
                                         IndicatorsCollector indicatorsCollector,
                                         MarketDataProvider marketDataProvider,
                                         BasicStrategy basicStrategy) {
        return new ProcessStarter(cacheDepthProvider,
                indicatorsCollector,
                marketDataProvider,
                basicStrategy);
    }

    @Bean
    public IndicatorsCollector indicatorsCollector(AbsolutePriceOscillator absolutePriceOscillator,
                                                   ExponentialMovingAverage exponentialMovingAverage,
                                                   RelativeStrengthIndicator relativeStrengthIndicator,
                                                   SimpleMovingAverage simpleMovingAverage,
                                                   StandardDerivatives standardDerivatives,
                                                   BollingerBands bollingerBands,
                                                   CoreStockService coreStockService) {
        return new IndicatorsCollector(absolutePriceOscillator,
                exponentialMovingAverage,
                relativeStrengthIndicator,
                simpleMovingAverage,
                standardDerivatives,
                bollingerBands,
                coreStockService);
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
                                       CoreStockService coreStockService,
                                       PositionService positionService) {
        return new BasicStrategy(fixProfitProvider,
                stopLossProvider,
                coreStockService,
                positionService);
    }

    @Bean
    public CacheDepthProvider btcCacheDepthProvider() {
        return new BtcCacheDepthProvider();
    }
}
