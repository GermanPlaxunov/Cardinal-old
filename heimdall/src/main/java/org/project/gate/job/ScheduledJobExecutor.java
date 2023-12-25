package org.project.gate.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ScheduledJobExecutor {


    public void trade() {
        log.info("trade");
    }

    public void train() {
        log.info("train");
    }

}
