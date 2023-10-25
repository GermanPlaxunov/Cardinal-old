package org.project.data.config;

import org.project.data.cache.CacheDepthMapper;
import org.project.data.cache.CacheDepthProvider;
import org.project.data.cache.CacheDepthProviderImpl;
import org.project.data.repositories.*;
import org.project.data.repositories.indicators.*;
import org.project.data.repositories.neural.NeuralNetworkRepository;
import org.project.data.services.classes.*;
import org.project.data.services.classes.indicators.*;
import org.project.data.services.classes.neural.NeuralNetworkServiceImpl;
import org.project.data.services.interfaces.*;
import org.project.data.services.interfaces.indicators.*;
import org.project.data.services.interfaces.neural.NeuralNetworkService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "org.project.data.repositories")
public class DataBeansConfig {

    @Bean
    public CoreStockService coreStockService(ProcessParamsService processParamsService,
                                             CoreStockRepository coreStockRepository) {
        return new CoreStockServiceImpl(processParamsService,
                coreStockRepository);
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
                                           @Value("9b6afcd3-8126-4ca9-a871-e66f409e1d68") String accountId) {
        return new PositionServiceImpl(positionRepository, accountId);
    }

    @Bean
    public AbsolutePriceOscillatorService absolutePriceOscillatorService(AbsolutePriceOscillatorRepository repository) {
        return new AbsolutePriceOscillatorServiceImpl(repository);
    }

    @Bean
    public BollingerBandsService bollingerBandsService(BollingerBandsRepository repository) {
        return new BollingerBandsServiceImpl(repository);
    }

    @Bean
    public ExponentialMovingAverageService exponentialMovingAverageService(ExponentialMovingAverageRepository repository) {
        return new ExponentialMovingAverageImpl(repository);
    }

    @Bean
    public RelativeStrengthIndicatorService relativeStrengthIndicatorService(RelativeStrengthIndicatorRepository repository) {
        return new RelativeStrengthIndicatorServiceImpl(repository);
    }

    @Bean
    public SimpleMovingAverageService simpleMovingAverageService(SimpleMovingAverageRepository repository) {
        return new SimpleMovingAverageServiceImpl(repository);
    }

    @Bean
    public StandardDerivativesService standardDerivativesService(StandardDerivativesRepository repository) {
        return new StandardDerivativesServiceImpl(repository);
    }

    @Bean
    public NeuralNetworkService neuralNetworkService(NeuralNetworkRepository neuralNetworkRepository) {
        return new NeuralNetworkServiceImpl(neuralNetworkRepository);
    }

    @Bean
    public LastProvidedStockService lastProvidedStockService(LastProvidedStockRepository lastProvidedStockRepository) {
        return new LastProvidedStockServiceImpl(lastProvidedStockRepository);
    }

    @Bean
    public MarketStockService marketStockService(MarketStockRepository marketStockRepository) {
        return new MarketStockServiceImpl(marketStockRepository);
    }

    @Bean
    public JobService jobService(JobRepository jobRepository) {
        return new JobServiceImpl(jobRepository);
    }

    @Bean
    public CacheDepthProvider cacheDepthProvider(ProcessParamsService processParamsService,
                                                 CacheDepthMapper cacheDepthMapper,
                                                 CoreStockService coreStockService) {
        return new CacheDepthProviderImpl(processParamsService,
                cacheDepthMapper,
                coreStockService);
    }

    @Bean
    public CacheDepthMapper cacheDepthMapper() {
        return new CacheDepthMapper();
    }

    @Bean
    public NeuralPredictionService neuralPredictionService(NeuralPredictionRepository repository) {
        return new NeuralPredictionServiceImpl(repository);
    }

}
