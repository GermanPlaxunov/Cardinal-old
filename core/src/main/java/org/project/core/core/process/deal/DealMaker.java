package org.project.core.core.process.deal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.core.client.market.MarketClient;

@Slf4j
@RequiredArgsConstructor
public class DealMaker {

    private final MarketClient marketClient;

    public void openLongPosition(String stockName, Double amountCurr, Double price) {
        log.info("Open position symbol: {} amount: {}", stockName, amountCurr);
        marketClient.openLongPosition(stockName, amountCurr);
    }

    public void closeLongPosition(String stockName) {
        log.info("Close position symbol: {}", stockName);
        marketClient.closeLongPosition(stockName);
    }

}
