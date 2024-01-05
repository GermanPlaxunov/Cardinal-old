package org.project.market.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.market.mapper.StockMapper;
import org.project.market.process.MarketService;
import org.cardinal.model.MarketStock;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MarketController {
    private final MarketService marketService;
    private final StockMapper stockMapper;

    @PostMapping(path = "/getNextStock")
    public MarketStock getNextStock(@RequestParam(name = "stockName") String stockName) {
        var next = marketService.getNextStock(stockName);
        log.info("Next provided stock: {}", next);
        return stockMapper.map(next);
    }

    @PostMapping(path = "/openLongPosition")
    public void openLongPosition(@RequestParam(name = "stockName") String stockName,
                                 @RequestParam(name = "amountCurr") Double amountCurr) {
        marketService.openLongPosition(stockName, amountCurr);
    }

    @PostMapping(path = "/closeLongPosition")
    public void closeLongPosition(@RequestParam(name = "stockName") String stockName) {
        marketService.closeLongPosition(stockName);
    }
}
