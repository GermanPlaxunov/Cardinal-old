package org.cardinal.core.deal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.market.process.MarketService;

@Slf4j
@RequiredArgsConstructor
public class DealMaker {

    private final MarketService marketService;

    /**
     * Sends signal to market to
     * open new long position.
     *
     * @param symbol - stock name.
     */
    public void openLongPosition(String symbol, Double amountCurr) {
        log.info("Open position symbol: {} amount: {}", symbol, amountCurr);
        marketService.openLongPosition(symbol, amountCurr);
    }

    /**
     * Sends signal to market to
     * close current long position.
     *
     * @param symbol - stock name.
     */
    public void closeLongPosition(String symbol) {
        log.info("Close position symbol: {}", symbol);
        marketService.closeLongPosition(symbol);
    }

}
