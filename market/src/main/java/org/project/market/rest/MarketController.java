package org.project.market.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.market.mapper.StockMapper;
import org.project.market.trading.MarketService;
import org.project.model.MarketStock;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MarketController {
    private final MarketService marketService;
    private final StockMapper stockMapper;

    @PostMapping(path = "/getNextStock",
            consumes = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8",
            produces = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8")
    public MarketStock getNextStock(@RequestParam(name = "stockName") String stockName) {
        var next = marketService.getNextStock(stockName);
        log.info("Next provided stock: {}", next);
        return stockMapper.map(next);
    }

    @PostMapping(path = "/openLongPosition",
            consumes = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8",
            produces = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8")
    public void openLongPosition(@RequestParam(name = "stockName") String stockName,
                                 @RequestParam(name = "amountCurr") Double amountCurr) {
        marketService.openLongPosition(stockName, amountCurr);
    }

    @PostMapping(path = "/closeLongPosition",
            consumes = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8",
            produces = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8")
    public void closeLongPosition(@RequestParam(name = "stockName") String stockName) {
        marketService.closeLongPosition(stockName);
    }

    @PostMapping(path = "/isNewDealAllowed",
            consumes = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8",
            produces = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8")
    public Boolean isNewDealAllowed(@RequestParam("symbol") String symbol) {
        return marketService.isNewDealAllowed(symbol);
    }

}
