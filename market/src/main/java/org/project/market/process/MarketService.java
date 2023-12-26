package org.project.market.process;

import lombok.RequiredArgsConstructor;
import org.libra.data.entities.MarketStockEntity;
import org.libra.data.services.interfaces.LastProvidedStockService;
import org.libra.data.services.interfaces.MarketStockService;
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
            lastProvidedStockService.save(firstStock.getId(), symbol, firstStock.getDate());
            nextStock = firstStock;
        }
        return nextStock;
    }

    public void openLongPosition(String symbol, Double amountCurr) {
        positionProcessor.openPosition(symbol, amountCurr);
        accountProcessor.updateAccountAfterOpeningPosition(symbol, true);
    }

    public void closeLongPosition(String symbol) {
        positionProcessor.closePosition(symbol);
        accountProcessor.updateAccountAfterClosingPosition(symbol);
    }

}
