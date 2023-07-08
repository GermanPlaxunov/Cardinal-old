package org.project.core.config;

import org.project.core.client.MlFeignClient;
import org.project.core.core.process.ProcessStarter;
import org.project.core.core.process.deal.DealMaker;
import org.project.core.database.repository.MarketDealRepository;
import org.project.core.database.service.classes.MarketDealServiceImpl;
import org.project.core.database.service.interfaces.MarketDealService;
import org.project.core.market.MarketFeignClient;
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
        MarketDealRepository.class
})
public class CoreBeansConfig {

    @Bean
    public DealMaker dealMaker(MarketFeignClient marketFeignClient) {
        return new DealMaker(marketFeignClient);
    }

    @Bean
    public ProcessStarter processStarter() {
        return new ProcessStarter();
    }

    @Bean
    public MarketDealService marketDealService(MarketDealRepository marketDealRepository) {
        return new MarketDealServiceImpl(marketDealRepository);
    }

}
