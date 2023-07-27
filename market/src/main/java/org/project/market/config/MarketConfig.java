package org.project.market.config;

import org.project.data.repositories.AccountRepository;
import org.project.data.repositories.LastProvidedStockRepository;
import org.project.data.repositories.MarketStockRepository;
import org.project.data.repositories.PositionRepository;
import org.project.data.services.classes.AccountServiceImpl;
import org.project.data.services.classes.LastProvidedStockServiceImpl;
import org.project.data.services.classes.MarketStockServiceImpl;
import org.project.data.services.classes.PositionServiceImpl;
import org.project.data.services.interfaces.AccountService;
import org.project.data.services.interfaces.LastProvidedStockService;
import org.project.data.services.interfaces.MarketStockService;
import org.project.data.services.interfaces.PositionService;
import org.project.market.process.MarketService;
import org.project.market.process.account.AccountProcessor;
import org.project.market.process.position.PositionProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "org.project.data.entities")
@EnableJpaRepositories(basePackages = "org.project.data.repositories")
public class MarketConfig {

    @Bean
    public MarketStockService stockService(MarketStockRepository marketStockRepository) {
        return new MarketStockServiceImpl(marketStockRepository);
    }

    @Bean
    public LastProvidedStockService lastProvidedStockService(LastProvidedStockRepository repository) {
        return new LastProvidedStockServiceImpl(repository);
    }

    @Bean
    public AccountService accountService(AccountRepository repository) {
        return new AccountServiceImpl(repository);
    }

    @Bean
    public PositionService positionService(PositionRepository repository,
                                           @Value("${market.account.id}") String accountId) {
        return new PositionServiceImpl(repository, accountId);
    }

    @Bean
    public MarketService marketService(LastProvidedStockService lastProvidedStockService,
                                       MarketStockService marketStockService,
                                       PositionProcessor positionProcessor,
                                       AccountProcessor accountProcessor) {
        return new MarketService(lastProvidedStockService,
                marketStockService,
                positionProcessor,
                accountProcessor);
    }

    @Bean
    public PositionProcessor positionProcessor(LastProvidedStockService lastProvidedStockService,
                                               MarketStockService marketStockService,
                                               PositionService positionService,
                                               AccountService accountService) {
        return new PositionProcessor(lastProvidedStockService,
                marketStockService,
                positionService,
                accountService);
    }

    @Bean
    public AccountProcessor accountProcessor(PositionService positionService,
                                             AccountService accountService) {
        return new AccountProcessor(positionService,
                accountService);
    }

}
