package org.project.core.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

@FeignClient(name = "ml-client", url = "http://localhost:8000")
public interface MlFeignClient extends MlClient {

    @Override
    @PostMapping(path = "/analyze",
            consumes = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8",
            produces = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8")
    void analyze(@RequestParam(name = "dateFrom") String dateFrom,
                 @RequestParam(name = "dateTo") String dateTo,
                 @RequestParam(name = "symbol") String symbol);
}
