package org.project.gate.job;

import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@RequiredArgsConstructor
public class TradingJob implements Job {

    private final ScheduledJobExecutor executor;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        executor.trade();
    }
}
