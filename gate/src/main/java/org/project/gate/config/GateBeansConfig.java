package org.project.gate.config;

import org.project.gate.job.SimpleJobService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateBeansConfig {

    @Bean
    public SimpleJobService simpleJobService() {
        return new SimpleJobService();
    }

}
