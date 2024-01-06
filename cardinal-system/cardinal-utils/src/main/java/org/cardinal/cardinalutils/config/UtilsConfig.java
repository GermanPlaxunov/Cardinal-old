package org.cardinal.cardinalutils.config;

import org.cardinal.cardinalutils.mapper.CandleMapper;
import org.cardinal.cardinalutils.mapper.ShareMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilsConfig {

    @Bean
    public CandleMapper candleMapper() {
        return new CandleMapper();
    }

    @Bean
    public ShareMapper shareMapper() {
        return new ShareMapper();
    }
}
