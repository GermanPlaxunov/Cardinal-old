package org.project.core.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.core.client.MlFeignClient;
import org.project.core.core.process.ProcessStarter;
import org.project.core.ml.MlRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.print.attribute.standard.Media;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CoreController {

    private final ProcessStarter processStarter;
    private final MlFeignClient mlFeignClient;

    @PostMapping(path = "/startProcess",
            consumes = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8",
            produces = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8")
    public void startProcess(@RequestParam(name = "stock") String stock) {
        processStarter.startProcess(stock);
    }

    @PostMapping(path = "/callMl",
            consumes = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8",
            produces = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8")
    public void callMl(@RequestBody MlRequest message) {
        var response = mlFeignClient.check(message.getMessage());
        log.info("Response from ML: {}", response);
    }

}
