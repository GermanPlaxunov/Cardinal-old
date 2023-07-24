package org.project.core.client;

import org.project.model.MarketStock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

@FeignClient(name = "market-client", url = "http://localhost:8082/market")
public interface MarketFeignClient extends MarketClient {

    @Override
    @PostMapping(path = "/getNextStock",
            consumes = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8",
            produces = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8")
    MarketStock getNextStock(@RequestParam(name = "stockName") String stockName);

    @Override
    @PostMapping(path = "/openLongPosition",
            consumes = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8",
            produces = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8")
    void openLongPosition(@RequestParam(name = "stockName") String stockName,
                          @RequestParam(name = "amountCurr") Double amountCurr);

    @Override
    @PostMapping(path = "/closeLongPosition",
            consumes = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8",
            produces = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8")
    void closeLongPosition(@RequestParam(name = "stockName") String stockName);

    @PostMapping(path = "/isNewDealAllowed",
            consumes = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8",
            produces = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8")
    Boolean isNewDealAllowed(@RequestParam("symbol") String symbol);
}
