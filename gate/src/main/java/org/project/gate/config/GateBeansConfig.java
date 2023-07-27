package org.project.gate.config;

import org.project.data.repositories.JobRepository;
import org.project.data.services.classes.JobServiceImpl;
import org.project.data.services.interfaces.JobService;
import org.project.gate.client.CoreClient;
import org.project.gate.client.CoreFeignClient;
import org.project.gate.job.ScheduledJob;
import org.project.gate.job.ScheduledJobExecutor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "org.project.data.repositories")
@EnableFeignClients(clients = CoreFeignClient.class)
public class GateBeansConfig {

    @Bean
    public ScheduledJobExecutor scheduledJobExecutor(JobService jobService,
                                                     CoreClient coreClient) {
        return new ScheduledJobExecutor(jobService,
                coreClient);
    }

    @Bean
    public ScheduledJob scheduledJob(ScheduledJobExecutor scheduledJobExecutor) {
        return new ScheduledJob(scheduledJobExecutor);
    }

    @Bean
    public JobService jobService(JobRepository jobRepository) {
        return new JobServiceImpl(jobRepository);
    }

}
