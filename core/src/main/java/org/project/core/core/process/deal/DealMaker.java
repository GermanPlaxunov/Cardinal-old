package org.project.core.core.process.deal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.core.client.MarketClient;
import org.project.data.services.interfaces.PositionService;

@Slf4j
@RequiredArgsConstructor
public class DealMaker {
    private final PositionService positionService;
    private final MarketClient marketClient;

    public void openLongPosition(String symbol, Double amountCurr) {
        var isAnyOpenPosition = positionService.ifOpenPosition(symbol);
        log.info("New deal is not allowed: {}", isAnyOpenPosition);
        if (!isAnyOpenPosition) {
            log.info("Open position symbol: {} amount: {}", symbol, amountCurr);
            marketClient.openLongPosition(symbol, amountCurr);
        }
    }

    public void closeLongPosition(String stockName) {
        log.info("Close position symbol: {}", stockName);
        marketClient.closeLongPosition(stockName);
    }

}
