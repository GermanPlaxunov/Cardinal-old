package org.cardinal.cardinalapp.config;

import org.cardinal.gate.config.GateBeansConfig;
import org.cardinal.core.config.CoreBeansConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CoreBeansConfig.class, GateBeansConfig.class})
public class CardinalConfig {
}
