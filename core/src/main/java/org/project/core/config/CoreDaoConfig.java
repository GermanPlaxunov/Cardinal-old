package org.project.core.config;

import org.project.data.repositories.CoreStockRepository;
import org.project.data.repositories.PositionRepository;
import org.project.data.repositories.PriceDiffSignalRepository;
import org.project.data.repositories.ProcessParamsRepository;
import org.project.data.services.classes.CoreStockServiceImpl;
import org.project.data.services.classes.PositionServiceImpl;
import org.project.data.services.classes.PriceDiffSignalServiceImpl;
import org.project.data.services.classes.ProcessParamsServiceImpl;
import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.PositionService;
import org.project.data.services.interfaces.PriceDiffSignalService;
import org.project.data.services.interfaces.ProcessParamsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "org.project.data.entities")
@EnableJpaRepositories(basePackages = "org.project.data.repositories")
public class CoreDaoConfig {

    @Bean
    public CoreStockService coreStockService(CoreStockRepository coreStockRepository) {
        return new CoreStockServiceImpl(coreStockRepository);
    }

    @Bean
    public PriceDiffSignalService priceDiffSignalService(PriceDiffSignalRepository priceDiffSignalRepository) {
        return new PriceDiffSignalServiceImpl(priceDiffSignalRepository);
    }

    @Bean
    public ProcessParamsService processParamsService(ProcessParamsRepository processParamsRepository) {
        return new ProcessParamsServiceImpl(processParamsRepository);
    }

    @Bean
    public PositionService positionService(PositionRepository positionRepository,
                                           @Value("${core.account.id") String accountId) {
        return new PositionServiceImpl(positionRepository, accountId);
    }
}
