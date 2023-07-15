package org.project.core.core.market;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.core.client.market.MarketClient;
import org.project.core.database.entity.CoreStockEntity;
import org.project.core.database.service.interfaces.CoreStockService;
import org.project.core.mapper.StockMapper;
import org.project.model.MarketStock;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
public class MarketDataProvider {

    private final CoreStockService coreStockService;
    private final MarketClient marketClient;
    private final StockMapper stockMapper;

    public MarketStock getNextDataPoint(String symbols) {
        var lastStock = coreStockService.findLastStock(symbols);
        var nextStock = getNext(symbols, lastStock);
        log.info("Received stock: {}", nextStock);
        coreStockService.save(stockMapper.mapToCore(nextStock));
        return nextStock;
    }


    private MarketStock getNext(String symbol, CoreStockEntity coreStockEntity) {
        LocalDateTime prevStockDate = LocalDateTime.of(2010, 1, 1, 1, 1, 1, 1);
        if(coreStockEntity != null) {
            prevStockDate = coreStockEntity.getDate();
        }
        return marketClient.getNextStock(symbol, prevStockDate);
    }

}
