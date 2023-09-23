package org.project.neural.process.config;

import org.project.data.repositories.CoreStockRepository;
import org.project.data.repositories.ProcessParamsRepository;
import org.project.data.repositories.indicators.*;
import org.project.data.repositories.neural.NeuralNetworkRepository;
import org.project.data.services.classes.CoreStockServiceImpl;
import org.project.data.services.classes.ProcessParamsServiceImpl;
import org.project.data.services.classes.indicators.*;
import org.project.data.services.classes.neural.NeuralNetworkServiceImpl;
import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.ProcessParamsService;
import org.project.data.services.interfaces.indicators.*;
import org.project.data.services.interfaces.neural.NeuralNetworkService;
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

    @Bean
    public NeuralNetworkService neuralNetworkService(NeuralNetworkRepository repository) {
        return new NeuralNetworkServiceImpl(repository);
    }

    @Bean
    public AbsolutePriceOscillatorService absolutePriceOscillatorService(
            AbsolutePriceOscillatorRepository absolutePriceOscillatorRepository) {
        return new AbsolutePriceOscillatorServiceImpl(absolutePriceOscillatorRepository);
    }

    @Bean
    public BollingerBandsService bollingerBandsService(BollingerBandsRepository bollingerBandsRepository) {
        return new BollingerBandsServiceImpl(bollingerBandsRepository);
    }

    @Bean
    public ExponentialMovingAverageService exponentialMovingAverageService(
            ExponentialMovingAverageRepository exponentialMovingAverageRepository) {
        return new ExponentialMovingAverageImpl(exponentialMovingAverageRepository);
    }

    @Bean
    public SimpleMovingAverageService simpleMovingAverageService(
            SimpleMovingAverageRepository simpleMovingAverageRepository) {
        return new SimpleMovingAverageServiceImpl(simpleMovingAverageRepository);
    }

    @Bean
    public StandardDerivativesService standardDerivativesService(
            StandardDerivativesRepository standardDerivativesRepository) {
        return new StandardDerivativesServiceImpl(standardDerivativesRepository);
    }

}
