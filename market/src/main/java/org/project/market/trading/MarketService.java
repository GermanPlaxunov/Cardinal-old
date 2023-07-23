package org.project.market.trading;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.market.database.entity.StockEntity;
import org.project.market.database.service.interfaces.LastProvidedStockService;
import org.project.market.database.service.interfaces.StockService;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
public class MarketService {
    private final LastProvidedStockService lastProvidedStockService;
    private final PositionProcessor positionProcessor;
    private final StockService stockService;

    public StockEntity getNextStock(String symbol, LocalDateTime prevStockDate) {
        var next = stockService.findNext(symbol, prevStockDate);
        lastProvidedStockService.update(next.getId(), symbol);
        log.info("Last stock id for symbol {} is {}", symbol, next.getId());
        return next;
    }

    public void openLongPosition(String symbol, Double amountCurr) {
        var lastStockData = lastProvidedStockService.find(symbol);
        var lastStock = stockService.findById(lastStockData.getId());
        positionProcessor.openLongPosition(symbol, amountCurr, lastStock.getClose());
    }

    public void closeLongPosition(String symbol) {
        var lastStockData = lastProvidedStockService.find(symbol);
        var lastStock = stockService.findById(lastStockData.getId());
        positionProcessor.closeLongPosition(symbol, lastStock.getClose());
    }
}
