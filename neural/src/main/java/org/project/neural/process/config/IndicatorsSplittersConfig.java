package org.project.neural.process.config;

import org.project.data.entities.indicators.*;
import org.project.neural.process.training.dataset.splitters.IndicatorSplitter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IndicatorsSplittersConfig {

    @Bean
    public IndicatorSplitter<AbsolutePriceOscillatorEntity> indicatorSplitterApo() {
        return new IndicatorSplitter<>();
    }

    @Bean
    public IndicatorSplitter<BollingerBandsEntity> indicatorSplitterBband() {
        return new IndicatorSplitter<>();
    }

    @Bean
    public IndicatorSplitter<ExponentialMovingAverageEntity> indicatorSplitterEma() {
        return new IndicatorSplitter<>();
    }

    @Bean
    public IndicatorSplitter<RelativeStrengthIndicatorEntity> indicatorSplitterRsi() {
        return new IndicatorSplitter<>();
    }

    @Bean
    public IndicatorSplitter<SimpleMovingAverageEntity> indicatorSplitterSma() {
        return new IndicatorSplitter<>();
    }

    @Bean
    public IndicatorSplitter<StandardDerivativesEntity> indicatorSplitterStd() {
        return new IndicatorSplitter<>();
    }

}
