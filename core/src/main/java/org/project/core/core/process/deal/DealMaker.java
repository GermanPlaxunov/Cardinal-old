package org.project.core.core.process.deal;

import lombok.RequiredArgsConstructor;
import org.project.core.market.MarketClient;

@RequiredArgsConstructor
public class DealMaker {

    private final MarketClient marketClient;

    public void openLongPosition(String stockName, Double amountCurr) {
        marketClient.openLongPosition(stockName, amountCurr);
    }

    public void closeLongPosition(String stockName) {
        marketClient.closeLongPosition(stockName);
    }

}
