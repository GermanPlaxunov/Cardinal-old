package org.project.market.process;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.MarketStockEntity;
import org.project.data.services.interfaces.LastProvidedStockService;
import org.project.data.services.interfaces.MarketStockService;
import org.project.market.process.account.AccountProcessor;
import org.project.market.process.position.PositionProcessor;


@RequiredArgsConstructor
public class MarketService {

    private final LastProvidedStockService lastProvidedStockService;
    private final MarketStockService marketStockService;
    private final PositionProcessor positionProcessor;
    private final AccountProcessor accountProcessor;

    public MarketStockEntity getNextStock(String symbol) {
        MarketStockEntity nextStock;
        if (lastProvidedStockService.exists(symbol)) {
            var lastStockData = lastProvidedStockService.find(symbol);
            var lastStock = marketStockService.findById(lastStockData.getStockId());
            nextStock = marketStockService.findNext(symbol, lastStock.getDate());
            lastProvidedStockService.update(nextStock.getId(), symbol, nextStock.getDate());
        } else {
            var firstStock = marketStockService.findFirst(symbol);
            lastProvidedStockService.init(symbol, firstStock.getId(), firstStock.getDate());
            nextStock = firstStock;
        }
        return nextStock;
    }

    public void openLongPosition(String symbol, Double amountCurr) {
        positionProcessor.createPosition(symbol, amountCurr);
        accountProcessor.updateAccount(symbol, true);
    }

    public void closeLongPosition(String symbol) {

    }

}
