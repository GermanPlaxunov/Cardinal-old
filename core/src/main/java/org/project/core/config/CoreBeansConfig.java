package org.project.core.config;

import org.project.core.client.MlFeignClient;
import org.project.core.client.market.MarketClient;
import org.project.core.client.market.MarketFeignClient;
import org.project.core.core.market.MarketDataProvider;
import org.project.core.core.process.ProcessStarter;
import org.project.core.core.process.deal.DealMaker;
import org.project.core.core.process.strategy.PriceDiffStrategyProcess;
import org.project.core.core.process.strategy.diff.DiffSignalCalculator;
import org.project.core.database.service.interfaces.CoreStockService;
import org.project.core.database.service.interfaces.PriceDiffSignalService;
import org.project.core.mapper.PriceDiffSignalMapper;
import org.project.core.mapper.StockMapper;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = {
        MarketFeignClient.class,
        MlFeignClient.class
})
public class CoreBeansConfig {

    @Bean
    public DealMaker dealMaker(MarketFeignClient marketFeignClient) {
        return new DealMaker(marketFeignClient);
    }

    @Bean
    public ProcessStarter processStarter(PriceDiffStrategyProcess priceDiffStrategyProcess,
                                         MarketDataProvider marketDataProvider) {
        return new ProcessStarter(priceDiffStrategyProcess,
                marketDataProvider);
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
    public DiffSignalCalculator diffSignalCalculator(PriceDiffSignalService priceDiffSignalService,
                                                     PriceDiffSignalMapper priceDiffSignalMapper,
                                                     CoreStockService coreStockService) {
        return new DiffSignalCalculator(priceDiffSignalService,
                priceDiffSignalMapper,
                coreStockService);
    }

    @Bean
    public PriceDiffStrategyProcess priceDiffStrategyProcess(PriceDiffSignalService priceDiffSignalService) {
        return new PriceDiffStrategyProcess(priceDiffSignalService);
    }

}
