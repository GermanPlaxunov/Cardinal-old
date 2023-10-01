package org.project.core.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "neural-client", url = "http://localhost:8083/neural")
public interface NeuralFeignClient extends NeuralClient {

    @Override
    @PostMapping(path = "/predict")
    Double predict(@RequestParam(name = "symbol") String symbol,
                   @RequestParam(name = "indicator") String indicator);

}
