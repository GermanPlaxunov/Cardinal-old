package org.project.market.trading.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.market.database.service.interfaces.AccountService;

@Slf4j
@RequiredArgsConstructor
public class AccountBalanceCalculator {

    private final AccountService accountService;

    public Double updateAccountBalance(String accountId, Double currAmt, Double price, boolean isOpening) {
        var change = currAmt * price;
        if (isOpening) {
            change *= (-1);
        }
        accountService.updateBalance(accountId, change);
        return change;
    }

}
