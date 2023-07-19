package org.project.core.config;

import org.project.core.database.repository.CoreStockRepository;
import org.project.core.database.repository.MarketDealRepository;
import org.project.core.database.repository.PriceDiffSignalRepository;
import org.project.core.database.service.classes.CoreStockServiceImpl;
import org.project.core.database.service.classes.MarketDealServiceImpl;
import org.project.core.database.service.classes.PriceDiffSignalServiceImpl;
import org.project.core.database.service.interfaces.CoreStockService;
import org.project.core.database.service.interfaces.MarketDealService;
import org.project.core.database.service.interfaces.PriceDiffSignalService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = {
        MarketDealRepository.class, CoreStockRepository.class,
        PriceDiffSignalRepository.class
})
public class CoreDaoConfig {

    @Bean
    public MarketDealService marketDealService(MarketDealRepository marketDealRepository) {
        return new MarketDealServiceImpl(marketDealRepository);
    }

    @Bean
    public CoreStockService coreStockService(CoreStockRepository repository) {
        return new CoreStockServiceImpl(repository);
    }

    @Bean
    public PriceDiffSignalService priceDiffSignalService(PriceDiffSignalRepository repository) {
        return new PriceDiffSignalServiceImpl(repository);
    }

}
