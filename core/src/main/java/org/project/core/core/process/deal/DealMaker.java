package org.project.core.core.process.deal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.core.client.MarketClient;

@Slf4j
@RequiredArgsConstructor
public class DealMaker {

    private final MarketClient marketClient;

    /**
     * Sends signal to market to
     * open new long position.
     *
     * @param symbol - stock name.
     */
    public void openLongPosition(String symbol, Double amountCurr) {
        log.info("Open position symbol: {} amount: {}", symbol, amountCurr);
        marketClient.openLongPosition(symbol, amountCurr);
    }

    /**
     * Sends signal to market to
     * close current long position.
     *
     * @param symbol - stock name.
     */
    public void closeLongPosition(String symbol) {
        log.info("Close position symbol: {}", symbol);
        marketClient.closeLongPosition(symbol);
    }

}
