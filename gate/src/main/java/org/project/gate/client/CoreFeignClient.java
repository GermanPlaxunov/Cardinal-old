package org.project.gate.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.WebUtils;

import java.net.URI;

@FeignClient(name = "core-client", url = "http://localhost:8080")
public interface CoreFeignClient extends CoreClient {

    @Override
    @PostMapping(path = "/startProcess",
            consumes = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8",
            produces = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8")
    void startProcess(URI uri);

}
