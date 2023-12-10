package org.project.market.process.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.data.entities.PositionEntity;
import org.project.data.services.interfaces.AccountService;
import org.project.data.services.interfaces.PositionService;

@Slf4j
@RequiredArgsConstructor
public class AccountProcessor {
    private final CommissionService commissionService;
    private final PositionService positionService;
    private final AccountService accountService;

    public void updateAccountAfterOpeningPosition(String symbol, boolean isOpening) {
        var position = positionService.findOpenPosition(symbol);
        var account = accountService.findActiveAccount();
        var price = getPrice(position, isOpening);
        var commission = commissionService.calculateBuyCommission(symbol, price, position.getCurrAmt());
        var balanceDelta = getAccountBalanceDelta(position.getCurrAmt(), price, commission, isOpening);
        var openPositions = getOpenPositions(isOpening);
        accountService.updateAccount(account.getAccountId(), balanceDelta, openPositions);
        log.info("Current account balance: {}", account.getBalance());
    }

    public void updateAccountAfterClosingPosition(String symbol) {
        var position = positionService.findLatestClosedPosition(symbol);
        var account = accountService.findActiveAccount();
        var income = position.getCurrAmt() * position.getClosePrice();
        var commission = commissionService.calculateSellCommission(symbol, position.getClosePrice(), position.getCurrAmt());
        accountService.updateAccount(account, income, commission, 0);
    }

    /**
     * Calculates account balance delta.
     *
     * @param currAmt    - amount of stocks
     * @param price      - price of each stock
     * @param commission - commission size
     * @param isOpening  - open position flag
     * @return final balance size
     */
    private Double getAccountBalanceDelta(Double currAmt, Double price, Double commission, boolean isOpening) {
        var absChange = (currAmt * price) + commission;
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
