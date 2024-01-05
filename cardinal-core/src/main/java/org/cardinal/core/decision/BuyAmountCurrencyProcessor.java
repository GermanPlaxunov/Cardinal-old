package org.cardinal.core.decision;

import lombok.RequiredArgsConstructor;
import org.cardinal.data.services.interfaces.AccountService;
import org.cardinal.data.services.interfaces.ProcessParamsService;
import org.cardinal.model.CoreStock;
import org.cardinal.model.ProcessVars;

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
