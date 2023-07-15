package org.project.core.config;

import org.project.core.client.MlClient;
import org.project.core.client.MlFeignClient;
import org.project.core.client.market.MarketClient;
import org.project.core.client.market.MarketFeignClient;
import org.project.core.core.market.MarketDataProvider;
import org.project.core.core.process.ProcessStarter;
import org.project.core.core.process.deal.DealMaker;
import org.project.core.database.repository.CoreStockRepository;
import org.project.core.database.repository.MarketDealRepository;
import org.project.core.database.service.classes.CoreStockServiceImpl;
import org.project.core.database.service.classes.MarketDealServiceImpl;
import org.project.core.database.service.interfaces.CoreStockService;
import org.project.core.database.service.interfaces.MarketDealService;
import org.project.core.mapper.StockMapper;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableFeignClients(clients = {
        MarketFeignClient.class,
        MlFeignClient.class
})
@EnableJpaRepositories(basePackageClasses = {
        MarketDealRepository.class,
        CoreStockRepository.class
})
public class CoreBeansConfig {

    @Bean
    public DealMaker dealMaker(MarketFeignClient marketFeignClient) {
        return new DealMaker(marketFeignClient);
    }

    @Bean
    public ProcessStarter processStarter(MarketDataProvider marketDataProvider,
                                         CoreStockService coreStockService,
                                         MlClient mlClient) {
        return new ProcessStarter(marketDataProvider,
                coreStockService,
                mlClient);
    }

    @Bean
    public MarketDealService marketDealService(MarketDealRepository marketDealRepository) {
        return new MarketDealServiceImpl(marketDealRepository);
    }

    @Bean
    public CoreStockService coreStockService(CoreStockRepository repository) {
        return new CoreStockServiceImpl(repository);
    }

    @Bean
    public MarketDataProvider marketDataProvider(CoreStockService coreStockService,
                                                 MarketClient marketClient,
                                                 StockMapper stockMapper) {
        return new MarketDataProvider(coreStockService,
                marketClient,
                stockMapper);
    }

}
