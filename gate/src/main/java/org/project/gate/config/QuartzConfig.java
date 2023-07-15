package org.project.gate.config;

import org.project.gate.job.ScheduledJob;
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
                .ofType(ScheduledJob.class)
                .withIdentity("Quartz_main_job")
                .withDescription("Main job")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail job) {
        return TriggerBuilder.newTrigger()
                .forJob(job)
                .withIdentity("Quartz_main_trigger")
                .withDescription("Trigger to launch price analyze")
                .withSchedule(simpleSchedule()
                        .repeatForever()
                        .withIntervalInSeconds(1))
                .build();
    }

}
