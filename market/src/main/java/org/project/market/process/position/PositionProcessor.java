package org.project.market.process.position;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.MarketStockEntity;
import org.project.data.entities.PositionEntity;
import org.project.data.services.interfaces.AccountService;
import org.project.data.services.interfaces.LastProvidedStockService;
import org.project.data.services.interfaces.MarketStockService;
import org.project.data.services.interfaces.PositionService;
import org.project.model.Constants;

@RequiredArgsConstructor
public class PositionProcessor {
    private final LastProvidedStockService lastProvidedStockService;
    private final MarketStockService marketStockService;
    private final PositionService positionService;
    private final AccountService accountService;

    public void createPosition(String symbol, Double currAmount) {
        var lastStockData = lastProvidedStockService.find(symbol);
        var lastStock = marketStockService.findById(lastStockData.getStockId());
        var entity = createEntity(lastStock, Constants.LONG_POSITION_TYPE, currAmount);
        positionService.save(entity);

    }

    private PositionEntity createEntity(MarketStockEntity stock, String type, Double currAmt) {
        var account = accountService.findActiveAccount();
        return new PositionEntity()
                .setType(type)
                .setAccountId(account.getAccountId())
                .setSymbol(stock.getSymbol())
                .setCurrAmt(currAmt)
                .setOpenPrice(stock.getClose())
                .setOpenDate(stock.getDate())
                .setAccountBalance(account.getBalance())
                .setIsOpen(Boolean.TRUE);
    }

}
