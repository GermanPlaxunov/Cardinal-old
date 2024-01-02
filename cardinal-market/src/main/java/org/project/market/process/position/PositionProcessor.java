package org.project.market.process.position;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cardinal.data.entities.MarketStockEntity;
import org.cardinal.data.entities.PositionEntity;
import org.cardinal.data.services.interfaces.AccountService;
import org.cardinal.data.services.interfaces.LastProvidedStockService;
import org.cardinal.data.services.interfaces.MarketStockService;
import org.cardinal.data.services.interfaces.PositionService;
import org.project.model.Constants;

@Slf4j
@RequiredArgsConstructor
public class PositionProcessor {
    private final LastProvidedStockService lastProvidedStockService;
    private final MarketStockService marketStockService;
    private final PositionService positionService;
    private final AccountService accountService;

    public void openPosition(String symbol, Double currAmount) {
        var lastStockData = lastProvidedStockService.find(symbol);
        var lastStock = marketStockService.findById(lastStockData.getStockId());
        var entity = createEntity(lastStock, Constants.LONG_POSITION_TYPE, currAmount);
        log.info("New Position: {}", entity);
        positionService.save(entity);
    }

    public void closePosition(String symbol) {
        var openPosition = positionService.findOpenPosition(symbol);
        var lastStockData = lastProvidedStockService.find(symbol);
        var lastStock = marketStockService.findById(lastStockData.getStockId());
        var account = accountService.findActiveAccount();
        var profit = getProfit(openPosition, account.getBalance(), lastStock.getClose());
        var closedPosition = updatePosition(openPosition, lastStock, profit);
        log.info("Close position: {}", closedPosition);
        positionService.save(closedPosition);
    }

    private Double getProfit(PositionEntity position, Double currBalance, Double closePrice) {
        var newBalance = currBalance + (position.getCurrAmt() * closePrice);
        return newBalance - position.getAccountBalance();
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

    private PositionEntity updatePosition(PositionEntity entity, MarketStockEntity lastStock, Double profit) {
        entity.setClosePrice(lastStock.getClose())
                .setCloseDate(lastStock.getDate())
                .setProfit(profit)
                .setIsOpen(Boolean.FALSE);
        return entity;
    }


}
