package org.project.market.process.position;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cardinal.data.services.interfaces.ProcessParamsService;

@Slf4j
@RequiredArgsConstructor
public class CommissionProcessor {

    private final ProcessParamsService processParamsService;

    /**
     * Calculates commission for opening new position.
     *
     * @param symbol                 - stock name
     * @param currentPriceOfTheStock - last obtained stock price
     * @param amount                 - amount of stocks in deal
     * @return commission size
     */
    public Double calculateCommissionOnBuy(String symbol, Double currentPriceOfTheStock, Double amount) {
        var percent = processParamsService.getBuyCommissionPercentage(symbol);
        var dealPrice = currentPriceOfTheStock * amount;
        return dealPrice * percent;
    }

    /**
     * Calculates commission for closing current position.
     *
     * @param symbol                 - stock price
     * @param currentPriceOfTheStock - last obtained stock price
     * @param amount                 - amount of stocks in deal
     * @return commission size
     */
    public Double calculateCommissionOnSell(String symbol, Double currentPriceOfTheStock, Double amount) {
        var percent = processParamsService.getSellCommissionPercentage(symbol);
        var dealPrice = currentPriceOfTheStock * amount;
        return dealPrice * percent;
    }

}
