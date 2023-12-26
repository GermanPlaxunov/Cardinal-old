package org.project.core.core.market;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.core.client.MarketClient;
import org.project.core.mapper.StockMapper;
import org.libra.data.services.interfaces.CoreStockService;
import org.project.model.MarketStock;

@Slf4j
@RequiredArgsConstructor
public class MarketDataProvider {

    private final CoreStockService coreStockService;
    private final MarketClient marketClient;
    private final StockMapper stockMapper;

    public MarketStock getNextDataPoint(String symbols) {
        var nextStock = getNext(symbols);
        log.info("Received stock: {}", nextStock);
        coreStockService.save(stockMapper.mapToCoreStockEntity(nextStock));
        return nextStock;
    }

    private MarketStock getNext(String symbol) {
        return marketClient.getNextStock(symbol);
    }
}
