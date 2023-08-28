package org.project.neural.process.config;

import org.project.data.repositories.CoreStockRepository;
import org.project.data.repositories.ProcessParamsRepository;
import org.project.data.repositories.indicators.RelativeStrengthIndicatorRepository;
import org.project.data.services.classes.CoreStockServiceImpl;
import org.project.data.services.classes.ProcessParamsServiceImpl;
import org.project.data.services.classes.indicators.RelativeStrengthIndicatorServiceImpl;
import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.ProcessParamsService;
import org.project.data.services.interfaces.indicators.RelativeStrengthIndicatorService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "org.project.data.entities")
@EnableJpaRepositories(basePackages = "org.project.data.repositories")
public class DaoConfig {

    @Bean
    public CoreStockService coreStockService(CoreStockRepository coreStockRepository) {
        return new CoreStockServiceImpl(coreStockRepository);
    }

    @Bean
    public RelativeStrengthIndicatorService relativeStrengthIndicatorService(
            RelativeStrengthIndicatorRepository repository) {
        return new RelativeStrengthIndicatorServiceImpl(repository);
    }

    @Bean
    public ProcessParamsService processParamsService(ProcessParamsRepository repository) {
        return new ProcessParamsServiceImpl(repository);
    }

}
