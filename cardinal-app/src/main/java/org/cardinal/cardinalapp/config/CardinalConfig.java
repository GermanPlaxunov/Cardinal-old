package org.cardinal.cardinalapp.config;

import org.cardinal.cardinalapp.process.MainProcessStarter;
import org.cardinal.cardinalbroker.config.BrokerBeansConfig;
import org.cardinal.gate.config.GateBeansConfig;
import org.cardinal.core.config.CoreBeansConfig;
import org.cardinal.model.job.ProcessStarter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CoreBeansConfig.class, GateBeansConfig.class,
        BrokerBeansConfig.class})
public class CardinalConfig {

    @Bean
    public ProcessStarter processStarter() {
        return new MainProcessStarter();
    }

}
