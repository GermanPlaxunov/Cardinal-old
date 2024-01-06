package org.cardinal.gate.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cardinal.model.job.ProcessStarter;

@Slf4j
@RequiredArgsConstructor
public class ScheduledJobExecutor {

    private final ProcessStarter processStarter;

    public void trade() {
        processStarter.startProcess();
    }

    public void train() {
        log.info("train");
    }

}
