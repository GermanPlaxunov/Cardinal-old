package org.libra.cardinal.config;

import org.libra.data.config.DataBeansConfig;
import org.project.core.config.CoreBeansConfig;
import org.project.core.mapper.StockMapper;
import org.project.core.mapper.StockMapperImpl;
import org.project.gate.config.GateBeansConfig;
import org.project.gate.job.ScheduledJobExecutor;
import org.project.model.job.ProcessStarter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({GateBeansConfig.class,
        DataBeansConfig.class,
        CoreBeansConfig.class})
public class CardinalConfiguration {

    @Bean
    public ScheduledJobExecutor scheduledJobExecutor(ProcessStarter processStarter) {
        return new ScheduledJobExecutor(processStarter);
    }

    @Bean
    public StockMapper stockMapper() {
        return new StockMapperImpl();
    }

}
