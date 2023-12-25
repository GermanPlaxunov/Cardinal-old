package org.libra.cardinal.config;

import org.libra.bragi.config.BragiBeansConfig;
import org.project.gate.config.HeimdallBeansConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({HeimdallBeansConfig.class,
        BragiBeansConfig.class})
public class CardinalConfiguration {


}
