package org.project.core.config;

import org.project.core.client.MarketClient;
import org.project.core.client.MarketFeignClient;
import org.project.core.core.market.MarketDataProvider;
import org.project.core.core.process.ProcessStarter;
import org.project.core.core.process.deal.DealMaker;
import org.project.core.core.process.strategy.PriceDiffStrategyProcess;
import org.project.core.core.process.strategy.diff.DiffSignalCalculator;
import org.project.core.database.service.interfaces.CoreStockService;
import org.project.core.database.service.interfaces.PriceDiffSignalService;
import org.project.core.mapper.PriceDiffSignalMapper;
import org.project.core.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Value;
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
    public PriceDiffStrategyProcess priceDiffStrategyProcess(PriceDiffSignalService priceDiffSignalService,
                                                             DiffSignalCalculator diffSignalCalculator,
                                                             DealMaker dealMaker,
                                                             @Value("${core.trading.position.amount}") Double positionAmount) {
        return new PriceDiffStrategyProcess(priceDiffSignalService,
                diffSignalCalculator,
                positionAmount,
                dealMaker);
    }

}
