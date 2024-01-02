package org.cardinal.core.market;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cardinal.core.mapper.CoreStockMapper;
import org.cardinal.data.services.interfaces.CoreStockService;
import org.project.market.process.MarketService;
import org.project.model.MarketStock;

@Slf4j
@RequiredArgsConstructor
public class MarketDataProvider {

    private final MarketService marketService;
    private final org.project.market.mapper.StockMapper marketStockMapper;
    private final CoreStockService coreStockService;
    private final CoreStockMapper coreStockMapper;

    /**
     * Returns true if next value of the stock
     * is available.
     *
     * @param symbol - stock name
     * @return true/false
     */
    public boolean hasNextStock(String symbol) {
        return true;
    }

    /**
     * Retrieves next value of the stock, saves it in DB
     * maps to CoreStock and returns.
     *
     * @param symbols - the name of the stock
     * @return next stock
     */
    public void saveNextDataPoint(String symbols) {
        var nextStock = getNext(symbols);
        log.info("Received stock: {}", nextStock);
        var stock = coreStockMapper.mapToCoreStockEntity(nextStock);
        coreStockService.save(stock);
    }

    private MarketStock getNext(String symbol) {
        var stock = marketService.getNextStock(symbol);
        return marketStockMapper.map(stock);
    }
}
