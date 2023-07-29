package org.project.market.process.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.data.entities.PositionEntity;
import org.project.data.services.interfaces.AccountService;
import org.project.data.services.interfaces.PositionService;

@Slf4j
@RequiredArgsConstructor
public class AccountProcessor {
    private final PositionService positionService;
    private final AccountService accountService;

    public void updateAccountAfterOpeningPosition(String symbol, boolean isOpening) {
        var position = positionService.findOpenPosition(symbol);
        var account = accountService.findActiveAccount();
        var price = getPrice(position, isOpening);
        var balanceDelta = getAccountBalanceDelta(position.getCurrAmt(), price, isOpening);
        var openPositions = getOpenPositions(isOpening);
        accountService.updateAccount(account.getAccountId(), balanceDelta, openPositions);
        log.info("Current account balance: {}", account.getBalance());
    }

    public void updateAccountAfterClosingPosition(String symbol) {
        var position = positionService.findLatestClosedPosition(symbol);
        var account = accountService.findActiveAccount();
        var income = position.getCurrAmt() * position.getClosePrice();
        accountService.updateAccount(account, income, 0);
    }

    private Double getAccountBalanceDelta(Double currAmt, Double price, boolean isOpening) {
        var absChange = currAmt * price;
        return isOpening
                ? absChange * (-1)
                : absChange;
    }

    private Double getPrice(PositionEntity entity, boolean isOpening) {
        return isOpening
                ? entity.getOpenPrice()
                : entity.getClosePrice();
    }

    private int getOpenPositions(boolean isOpening) {
        return isOpening ? 1 : 0;
    }

}
