package org.project.gate.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.gate.database.service.interfaces.JobService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GateController {

    private final JobService jobService;

    @PostMapping(path = "/saveNewJob")
    public void saveNewJob(@RequestParam(name = "name") String name,
                           @RequestParam(name = "interval") Long interval,
                           @RequestParam(name = "stock") String stock) {
        jobService.save(name, interval, stock);
        log.info("New job entity saved name: {}", name);
    }

}
