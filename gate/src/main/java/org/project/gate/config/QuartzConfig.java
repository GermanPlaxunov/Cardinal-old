package org.project.gate.config;

import org.project.gate.job.SimpleJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob()
                .ofType(SimpleJob.class)
                .withIdentity("Quartz_job")
                .withDescription("Test job")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail job) {
        return TriggerBuilder.newTrigger()
                .forJob(job)
                .withIdentity("Quartz_trigger")
                .withDescription("Simple trigger")
                .withSchedule(simpleSchedule().repeatForever().withIntervalInSeconds(5))
                .build();
    }

}
