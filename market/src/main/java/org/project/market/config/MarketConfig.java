package org.project.market.config;

import org.project.market.database.repository.LastProvidedStockRepository;
import org.project.market.database.repository.StockRepository;
import org.project.market.database.service.classes.LastProvidedStockServiceImpl;
import org.project.market.database.service.classes.StockServiceImpl;
import org.project.market.database.service.interfaces.LastProvidedStockService;
import org.project.market.database.service.interfaces.StockService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = {
        StockRepository.class, LastProvidedStockRepository.class
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

}
