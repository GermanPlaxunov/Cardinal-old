package org.project.core.config;

import org.project.core.core.process.decision.DecisionStarter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DecisionConfig {

    @Bean
    public DecisionStarter decisionStarter() {
        return new DecisionStarter();
    }

}
