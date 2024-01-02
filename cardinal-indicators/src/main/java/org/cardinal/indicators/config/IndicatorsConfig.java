package org.cardinal.indicators.config;

import org.cardinal.indicators.indicators.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IndicatorsConfig {

    @Bean
    public AbsolutePriceOscillator absolutePriceOscillator(ExponentialMovingAverage exponentialMovingAverage) {
        return new AbsolutePriceOscillator(exponentialMovingAverage);
    }

    @Bean
    public BollingerBands bollingerBands(ExponentialMovingAverage exponentialMovingAverage) {
        return new BollingerBands(exponentialMovingAverage);
    }

    @Bean
    public ExponentialMovingAverage exponentialMovingAverage() {
        return new ExponentialMovingAverage();
    }

    @Bean
    public RelativeStrengthIndicator relativeStrengthIndicator() {
        return new RelativeStrengthIndicator();
    }

    @Bean
    public SimpleMovingAverage simpleMovingAverage() {
        return new SimpleMovingAverage();
    }

    @Bean
    public StandardDerivatives standardDerivatives(SimpleMovingAverage simpleMovingAverage) {
        return new StandardDerivatives(simpleMovingAverage);
    }

}
