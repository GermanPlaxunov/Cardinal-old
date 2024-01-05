package org.project.market.process.position;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cardinal.data.entities.MarketStockEntity;
import org.cardinal.data.entities.PositionEntity;
import org.cardinal.data.services.interfaces.AccountService;
import org.cardinal.data.services.interfaces.LastProvidedStockService;
import org.cardinal.data.services.interfaces.MarketStockService;
import org.cardinal.data.services.interfaces.PositionService;
import org.cardinal.model.Constants;

@Slf4j
@RequiredArgsConstructor
public class PositionProcessor {

    private final LastProvidedStockService lastProvidedStockService;
    private final CommissionProcessor commissionProcessor;
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
        var symbol = stock.getSymbol();
        var account = accountService.findActiveAccount();
        var openCommission = commissionProcessor.calculateCommissionOnBuy(symbol, stock.getClose(), currAmt);
        return new PositionEntity()
                .setType(type)
                .setAccountId(account.getAccountId())
                .setSymbol(symbol)
                .setCurrAmt(currAmt)
                .setOpenPrice(stock.getClose())
                .setOpenCommission(openCommission)
                .setOpenDate(stock.getDate())
                .setAccountBalance(account.getBalance())
                .setIsOpen(Boolean.TRUE);
    }

    private PositionEntity updatePosition(PositionEntity entity, MarketStockEntity lastStock, Double profit) {
        var symbol = lastStock.getSymbol();
        var currentPrice = lastStock.getClose();
        var closeCommission = commissionProcessor.calculateCommissionOnSell(symbol, currentPrice, entity.getCurrAmt());
        entity.setClosePrice(closeCommission)
                .setCloseDate(lastStock.getDate())
                .setProfit(profit)
                .setCloseCommission(closeCommission)
                .setIsOpen(Boolean.FALSE);
        return entity;
    }


}
