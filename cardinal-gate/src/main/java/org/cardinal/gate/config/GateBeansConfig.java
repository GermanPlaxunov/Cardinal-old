package org.cardinal.gate.config;

import org.cardinal.data.config.DataBeansConfig;
import org.cardinal.gate.job.ScheduledJobExecutor;
import org.cardinal.gate.job.TradingJob;
import org.cardinal.gate.job.TrainingJob;
import org.cardinal.model.job.ProcessStarter;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@Configuration
@Import(DataBeansConfig.class)
public class GateBeansConfig {

    @Bean
    public ScheduledJobExecutor scheduledJobExecutor(ProcessStarter processStarter) {
        return new ScheduledJobExecutor(processStarter);
    }

    @Bean
    @Qualifier("tradingJob")
    public JobDetail tradingJob() {
        return JobBuilder.newJob()
                .ofType(TradingJob.class)
                .withIdentity("Quartz_trade_job")
                .withDescription("Trade job")
                .storeDurably()
                .build();
    }

    @Bean
    @Qualifier("trainingJob")
    public JobDetail trainingJob() {
        return JobBuilder.newJob()
                .ofType(TrainingJob.class)
                .withIdentity("Quartz_train_job")
                .withDescription("Train job")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger tradingTrigger(@Qualifier("tradingJob") JobDetail job) {
        return TriggerBuilder.newTrigger()
                .forJob(job)
                .withIdentity("Quartz_trading_trigger")
                .withDescription("Trigger to launch price analyze")
                .withSchedule(simpleSchedule()
                        .repeatForever()
                        .withIntervalInSeconds(2))
                .build();
    }

    @Bean
    public Trigger trainingTrigger(@Qualifier("trainingJob") JobDetail job) {
        return TriggerBuilder.newTrigger()
                .forJob(job)
                .withIdentity("Quartz_main_trigger")
                .withDescription("Trigger to launch model training analyze")
                .withSchedule(simpleSchedule()
                        .repeatForever()
                        .withIntervalInSeconds(30))
                .build();
    }

}