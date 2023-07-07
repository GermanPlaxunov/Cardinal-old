package org.project.core.config;

import org.project.core.core.process.ProcessStarter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreBeansConfig {

    @Bean
    public ProcessStarter processStarter() {
        return new ProcessStarter();
    }

}
