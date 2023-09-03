package org.project.gate.job;

import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@RequiredArgsConstructor
public class TrainingJob implements Job {

    private final ScheduledJobExecutor scheduledJobExecutor;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        scheduledJobExecutor.train();
    }
}
