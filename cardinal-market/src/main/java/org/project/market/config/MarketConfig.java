package org.project.market.config;

import org.cardinal.data.config.DataBeansConfig;
import org.cardinal.data.repositories.PositionRepository;
import org.cardinal.data.services.classes.PositionServiceImpl;
import org.cardinal.data.services.interfaces.*;
import org.project.market.process.MarketService;
import org.project.market.process.account.AccountProcessor;
import org.project.market.process.account.CommissionService;
import org.project.market.process.position.CommissionProcessor;
import org.project.market.process.position.PositionProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Import(DataBeansConfig.class)
@EntityScan(basePackages = "org.project.data.entities")
//@EnableJpaRepositories(basePackages = "org.cardinal.data.repositories")
public class MarketConfig {

//    @Bean
//    public PositionService positionService(PositionRepository repository,
//                                           @Value("9b6afcd3-8126-4ca9-a871-e66f409e1d68") String accountId) {
//        return new PositionServiceImpl(repository, accountId);
//    }

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
    public CommissionProcessor commissionProcessor(ProcessParamsService processParamsService) {
        return new CommissionProcessor(processParamsService);
    }

    @Bean
    public PositionProcessor positionProcessor(LastProvidedStockService lastProvidedStockService,
                                               CommissionProcessor commissionProcessor,
                                               MarketStockService marketStockService,
                                               PositionService positionService,
                                               AccountService accountService) {
        return new PositionProcessor(lastProvidedStockService,
                commissionProcessor,
                marketStockService,
                positionService,
                accountService);
    }

    @Bean
    public AccountProcessor accountProcessor(CommissionService commissionService,
                                             PositionService positionService,
                                             AccountService accountService) {
        return new AccountProcessor(commissionService,
                positionService,
                accountService);
    }

    @Bean
    public CommissionService commissionService(ProcessParamsService processParamsService) {
        return new CommissionService(processParamsService);
    }

}
