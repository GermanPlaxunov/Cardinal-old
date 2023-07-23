package org.project.market.config;

import org.project.market.database.repository.AccountRepository;
import org.project.market.database.repository.LastProvidedStockRepository;
import org.project.market.database.repository.PositionRepository;
import org.project.market.database.repository.StockRepository;
import org.project.market.database.service.classes.AccountServiceImpl;
import org.project.market.database.service.classes.LastProvidedStockServiceImpl;
import org.project.market.database.service.classes.PositionServiceImpl;
import org.project.market.database.service.classes.StockServiceImpl;
import org.project.market.database.service.interfaces.AccountService;
import org.project.market.database.service.interfaces.LastProvidedStockService;
import org.project.market.database.service.interfaces.PositionService;
import org.project.market.database.service.interfaces.StockService;
import org.project.market.trading.MarketService;
import org.project.market.trading.PositionProcessor;
import org.project.market.trading.account.AccountBalanceCalculator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = {
        StockRepository.class, LastProvidedStockRepository.class,
        AccountRepository.class
})
public class MarketConfig {

    @Bean
    public StockService stockService(StockRepository stockRepository) {
        return new StockServiceImpl(stockRepository);
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
                                       StockService stockService) {
        return new MarketService(lastProvidedStockService,
                positionProcessor,
                stockService);
    }

}
