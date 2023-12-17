package org.project.core.core.process.decision;

import lombok.RequiredArgsConstructor;
import org.project.data.services.interfaces.AccountService;
import org.project.data.services.interfaces.ProcessParamsService;
import org.project.model.CoreStock;
import org.project.model.ProcessVars;

@RequiredArgsConstructor
public class BuyAmountCurrencyProcessor {

    private final ProcessParamsService processParamsService;
    private final AccountService accountService;

    public Double getBuyAmountCurrency(ProcessVars<CoreStock> processVars) {
        var balance = getAccountBalance();
        var maxBalanceShare = processParamsService.getMaxBalanceShareForTrade();
        var currentPrice = processVars.getCurrentPrice();
        var totalPrice = getTotalDealPrice(balance, maxBalanceShare, processVars.getOpenPositionCommission());
        return totalPrice / currentPrice;
    }

    private Double getTotalDealPrice(Double balance, Double share, Double commission) {
        return (balance * share) - commission;
    }

    private Double getAccountBalance() {
        return accountService.findActiveAccount()
                .getBalance();
    }

}
