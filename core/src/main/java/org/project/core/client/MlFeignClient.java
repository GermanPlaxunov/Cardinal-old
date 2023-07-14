package org.project.core.client;

import org.project.core.ml.MlRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

@FeignClient(name = "ml-client", url = "http://localhost:8000")
public interface MlFeignClient extends MlClient {

    @Override
    @PostMapping(path = "/ml/check/",
            consumes = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8",
            produces = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8")
    String check(@RequestParam(name = "username") String username,
                 @RequestParam(name = "message") String message);
}
