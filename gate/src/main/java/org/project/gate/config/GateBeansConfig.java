package org.project.gate.config;

import org.project.gate.client.CoreClient;
import org.project.gate.client.CoreFeignClient;
import org.project.gate.database.repository.JobRepository;
import org.project.gate.database.service.classes.JobServiceImpl;
import org.project.gate.database.service.interfaces.JobService;
import org.project.gate.job.SimpleJobService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = {
        JobRepository.class
})
@EnableFeignClients(clients = CoreFeignClient.class)
public class GateBeansConfig {

    @Bean
    public SimpleJobService simpleJobService(CoreClient coreClient,
                                             @Value("${masada.core.url}") String url) {
        return new SimpleJobService(coreClient, url);
    }

    @Bean
    public JobService jobService(JobRepository jobRepository) {
        return new JobServiceImpl(jobRepository);
    }

}
