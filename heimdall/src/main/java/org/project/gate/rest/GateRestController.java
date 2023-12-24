package org.project.gate.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.data.services.interfaces.JobService;
import org.project.gate.job.JobData;
import org.project.gate.param.ParamData;
import org.project.gate.param.ParamsSaver;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GateRestController {
    private static final String CONSUMES = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8";
    private static final String PRODUCES = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8";
    private final ParamsSaver paramsSaver;
    private final JobService jobService;

    @PostMapping(path = "/saveNewJob", consumes = CONSUMES, produces = PRODUCES)
    public void saveNewJob(@RequestBody JobData jobData) {
        jobService.save(jobData.getName(), jobData.getInterval(), jobData.getStock());
        log.info("New job entity saved name: {}", jobData.getName());
    }

    @PostMapping(path = "/addProcessParam", consumes = CONSUMES, produces = PRODUCES)
    public void addProcessParam(@RequestBody ParamData data){
        paramsSaver.saveParam(data);
    }

}
