package org.libra.cardinal.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.libra.data.services.interfaces.JobService;
import org.project.model.job.AddJobData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CardinalRestController implements CardinalController {

    private final JobService jobService;

    @PostMapping(path = "/addNewJob")
    public void addNewJob(@RequestBody AddJobData addJobData) {
        jobService.save(addJobData);
    }

}
