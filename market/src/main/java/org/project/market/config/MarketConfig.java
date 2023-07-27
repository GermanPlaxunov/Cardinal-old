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
import org.project.market.trading.MarketService;
import org.project.market.trading.PositionProcessor;
import org.project.market.trading.account.AccountBalanceCalculator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
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
    public PositionProcessor positionProcessor(AccountBalanceCalculator accountBalanceCalculator,
                                               PositionService positionService,
                                               AccountService accountService,
                                               @Value("${market.account.id}") String accountId) {
        return new PositionProcessor(accountBalanceCalculator,
                positionService,
                accountService,
                accountId);
    }

    @Bean
    public AccountBalanceCalculator accountBalanceCalculator(AccountService accountService) {
        return new AccountBalanceCalculator(accountService);
    }

    @Bean
    public PositionService positionService(PositionRepository repository,
                                           @Value("${market.account.id}") String accountId) {
        return new PositionServiceImpl(repository, accountId);
    }

    @Bean
    public MarketService marketService(LastProvidedStockService lastProvidedStockService,
                                       PositionProcessor positionProcessor,
                                       AccountService accountService,
                                       MarketStockService marketStockService) {
        return new MarketService(lastProvidedStockService,
                positionProcessor,
                accountService,
                marketStockService);
    }

}
