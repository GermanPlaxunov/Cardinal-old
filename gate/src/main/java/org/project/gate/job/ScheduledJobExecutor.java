package org.project.gate.job;

import lombok.RequiredArgsConstructor;
import org.project.gate.client.CoreClient;
import org.project.gate.database.service.interfaces.JobService;

import java.net.URI;

@RequiredArgsConstructor
public class ScheduledJobExecutor {

    private final JobService jobService;
    private final CoreClient coreClient;
    private final String url;

    public void execute() {
        var job = jobService.findByStockName("BTC");
        coreClient.startProcess(URI.create(url), job.getStockName());
    }

}
