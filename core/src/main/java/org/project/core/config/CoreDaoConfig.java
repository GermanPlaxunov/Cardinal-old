package org.project.core.config;

import org.project.core.database.repository.CoreStockRepository;
import org.project.core.database.repository.PriceDiffSignalRepository;
import org.project.core.database.repository.ProcessParamsRepository;
import org.project.core.database.service.classes.CoreStockServiceImpl;
import org.project.core.database.service.classes.PriceDiffSignalServiceImpl;
import org.project.core.database.service.classes.ProcessParamsServiceImpl;
import org.project.core.database.service.interfaces.CoreStockService;
import org.project.core.database.service.interfaces.PriceDiffSignalService;
import org.project.core.database.service.interfaces.ProcessParamsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = {
        CoreStockRepository.class, PriceDiffSignalRepository.class,
        ProcessParamsRepository.class
})
public class CoreDaoConfig {

    @Bean
    public CoreStockService coreStockService(CoreStockRepository repository) {
        return new CoreStockServiceImpl(repository);
    }

    @Bean
    public PriceDiffSignalService priceDiffSignalService(PriceDiffSignalRepository repository) {
        return new PriceDiffSignalServiceImpl(repository);
    }

    @Bean
    public ProcessParamsService processParamsService(ProcessParamsRepository repository) {
        return new ProcessParamsServiceImpl(repository);
    }

}
