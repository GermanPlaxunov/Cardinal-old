package org.project.core.config;

import org.project.data.repositories.CoreStockRepository;
import org.project.data.repositories.PriceDiffSignalRepository;
import org.project.data.repositories.ProcessParamsRepository;
import org.project.data.services.classes.CoreStockServiceImpl;
import org.project.data.services.classes.PriceDiffSignalServiceImpl;
import org.project.data.services.classes.ProcessParamsServiceImpl;
import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.PriceDiffSignalService;
import org.project.data.services.interfaces.ProcessParamsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
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

}
