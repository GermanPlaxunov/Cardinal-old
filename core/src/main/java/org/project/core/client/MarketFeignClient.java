package org.project.core.client;

import org.project.model.MarketStock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "market-client", url = "http://localhost:8082/market")
public interface MarketFeignClient extends MarketClient {

    @Override
    @PostMapping(path = "/getNextStock")
    MarketStock getNextStock(@RequestParam(name = "stockName") String stockName);

    @Override
    @PostMapping(path = "/openLongPosition")
    void openLongPosition(@RequestParam(name = "stockName") String stockName,
                          @RequestParam(name = "amountCurr") Double amountCurr);

    @Override
    @PostMapping(path = "/closeLongPosition")
    void closeLongPosition(@RequestParam(name = "stockName") String stockName);

}
