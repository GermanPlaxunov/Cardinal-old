package org.project.market.trading.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.data.services.interfaces.AccountService;

@Slf4j
@RequiredArgsConstructor
public class AccountBalanceCalculator {

    private final AccountService accountService;

    public Double updateAccountBalance(String accountId, Double currAmt, Double price, boolean isOpening) {
        var change = currAmt * price;
        if (isOpening) {
            change *= (-1);
        }
        accountService.updateAccount(accountId, change, isOpening ? 1 : 0);
        return change;
    }

}
