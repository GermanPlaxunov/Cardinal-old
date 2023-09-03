package org.project.gate.config;

import org.project.data.repositories.JobRepository;
import org.project.data.services.classes.JobServiceImpl;
import org.project.data.services.interfaces.JobService;
import org.project.gate.client.CoreClient;
import org.project.gate.client.CoreFeignClient;
import org.project.gate.client.NeuralClient;
import org.project.gate.client.NeuralFeignClient;
import org.project.gate.job.ScheduledJobExecutor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "org.project.data.entities")
@EnableJpaRepositories(basePackages = "org.project.data.repositories")
@EnableFeignClients(clients = {CoreFeignClient.class,
        NeuralFeignClient.class})
public class GateBeansConfig {

    @Bean
    public ScheduledJobExecutor scheduledJobExecutor(NeuralClient neuralClient, 
                                                     CoreClient coreClient) {
        return new ScheduledJobExecutor(neuralClient,
                coreClient);
    }

    @Bean
    public JobService jobService(JobRepository jobRepository) {
        return new JobServiceImpl(jobRepository);
    }

}
