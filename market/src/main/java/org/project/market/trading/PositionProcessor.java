package org.project.market.trading;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.market.database.entity.PositionEntity;
import org.project.market.database.service.interfaces.AccountService;
import org.project.market.database.service.interfaces.PositionService;
import org.project.market.trading.account.AccountBalanceCalculator;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
public class PositionProcessor {
    private final String POSITION_TYPE_LONG = "LONG";
    private final AccountBalanceCalculator accountBalanceCalculator;
    private final PositionService positionService;
    private final AccountService accountService;
    private final String accountId;

    public void openLongPosition(String symbol, Double currAmt, Double price) {
        var account = accountService.findById(accountId);
        var balance = account.getBalance();
        var position = new PositionEntity()
                .setAccountId(accountId)
                .setSymbol(symbol)
                .setIsOpen(true)
                .setCurrAmt(currAmt)
                .setOpenPrice(price)
                .setType(POSITION_TYPE_LONG)
                .setAccountBalance(balance)
                .setOpenDate(LocalDateTime.now());
        positionService.save(position);
        accountBalanceCalculator.updateAccountBalance(accountId, currAmt, price, true);
        log.info("Balance for {} is {}", position.getOpenDate(), balance);
    }

    public void closeLongPosition(String symbol, Double closePrice) {
        var position = positionService.findOpenPosition(symbol);
        var income = accountBalanceCalculator.updateAccountBalance(accountId, position.getCurrAmt(), closePrice, false);
        var profit = getProfit(position.getAccountBalance(), income);
        position.setIsOpen(false)
                .setCloseDate(LocalDateTime.now())
                .setClosePrice(closePrice)
                .setProfit(profit);
        positionService.save(position);
        accountService.updateAccount(accountId, profit, 0);
        log.info("Profit for {} is {}", position.getCloseDate(), profit);
    }

    private Double getProfit(Double balanceBeforeDeal, Double income) {
        var account = accountService.findById(accountId);
        return (account.getBalance() + income) - balanceBeforeDeal;
    }

}
