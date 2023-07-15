package org.project.market.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.market.database.service.interfaces.StockService;
import org.project.market.mapper.StockMapper;
import org.project.model.MarketStock;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MarketController {

    private final StockService stockService;
    private final StockMapper stockMapper;

    @PostMapping(path = "/getNextStock",
            consumes = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8",
            produces = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8")
    public MarketStock getNextStock(@RequestParam(name = "stockName") String stockName,
                                    @RequestParam(name = "prevStockDate") LocalDateTime prevStockDate) {
        var next = stockService.findNext(stockName, prevStockDate);
        return stockMapper.map(next);
    }

    @PostMapping(path = "/openLongPosition",
            consumes = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8",
            produces = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8")
    public Long openLongPosition(@RequestParam(name = "stockName") String stockName,
                                 @RequestParam(name = "amountCurr") Double amountCurr) {

        return 0L;
    }

    @PostMapping(path = "/openLongPosition",
            consumes = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8",
            produces = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8")
    public void closeLongPosition(@RequestParam(name = "stockName") String stockName) {

    }

}
