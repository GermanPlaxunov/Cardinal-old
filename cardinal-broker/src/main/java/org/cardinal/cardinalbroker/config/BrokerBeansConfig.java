package org.cardinal.cardinalbroker.config;

import org.cardinal.cardinalbroker.account.SandBoxAccess;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrokerBeansConfig {

    @Bean
    public SandBoxAccess sandBoxAccess(@Value("${tinkoff.invest.token}") String accessToken) {
        return new SandBoxAccess(accessToken);
    }

}
