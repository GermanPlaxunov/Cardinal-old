package org.project.market.trading;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.data.entities.MarketStockEntity;
import org.project.data.services.interfaces.AccountService;
import org.project.data.services.interfaces.LastProvidedStockService;
import org.project.data.services.interfaces.MarketStockService;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
public class MarketService {
    private final LastProvidedStockService lastProvidedStockService;
    private final PositionProcessor positionProcessor;
    private final AccountService accountService;
    private final MarketStockService stockService;

    public MarketStockEntity getNextStock(String symbol) {
        var lastStockData = lastProvidedStockService.find(symbol);
        LocalDateTime prevStockDate = LocalDateTime.of(2000, 1, 1, 1, 1, 1, 1);
        var isInitial = lastStockData == null;
        if (!isInitial) {
            prevStockDate = lastStockData.getStockDate();
        }
        var next = stockService.findNext(symbol, prevStockDate);
        if (isInitial) {
            lastProvidedStockService.save(next.getId(), symbol, next.getDate());
        } else {
            lastProvidedStockService.update(next.getId(), symbol, next.getDate());
        }
        log.info("Last stock id for symbol {} is {}", symbol, next.getId());
        return next;
    }

    public void openLongPosition(String symbol, Double amountCurr) {
        var lastStockData = lastProvidedStockService.find(symbol);
        var lastStock = stockService.findById(lastStockData.getStockId());
        positionProcessor.openLongPosition(symbol, amountCurr, lastStock.getClose());
    }

    public void closeLongPosition(String symbol) {
        var lastStockData = lastProvidedStockService.find(symbol);
        var lastStock = stockService.findById(lastStockData.getStockId());
        log.info("Last stock: {}", lastStock);
        positionProcessor.closeLongPosition(symbol, lastStock.getClose());
    }

    public Boolean isNewDealAllowed(String symbol) {
        var accountId = "9b6afcd3-8126-4ca9-a871-e66f409e1d68";
        return !accountService.isAnyOpenPosition(accountId);
    }
}
