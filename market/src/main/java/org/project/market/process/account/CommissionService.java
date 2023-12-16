package org.project.market.process.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.data.services.interfaces.ProcessParamsService;

@Slf4j
@RequiredArgsConstructor
public class CommissionService {

    private final ProcessParamsService processParamsService;

    /**
     * Calculates commission size on opening new position.
     *
     * @param symbol     - name of the stock
     * @param openPrice  - price of the stock
     * @param amountCurr - amount of the stocks
     */
    public Double calculateBuyCommission(String symbol, Double openPrice, Double amountCurr) {
        var percent = processParamsService.getBuyCommissionPercentage(symbol);
        var totalPrice = getTotalBuyPrice(openPrice, amountCurr);
        var commission = totalPrice * percent;
        log.info("Commission of buying {} = {}", symbol, commission);
        return commission;
    }

    /**
     * Calculates commission size on closing new position.
     *
     * @param symbol       - name of the stock
     * @param currentPrice - price of the stock
     * @param amountCurr   - amount of the stocks
     */
    public Double calculateSellCommission(String symbol, Double currentPrice, Double amountCurr) {
        var percent = processParamsService.getSellCommissionPercentage(symbol);
        var totalPrice = getTotalSellPrice(currentPrice, amountCurr);
        var commission = totalPrice * percent;
        log.info("Commission of selling {} = {}", symbol, commission);
        return commission;
    }

    /**
     * Returns total price of buying stocks.
     *
     * @param openPrice  - price of the stock
     * @param amountCurr - amount of the stock
     * @return buy price
     */
    private Double getTotalBuyPrice(Double openPrice, Double amountCurr) {
        return openPrice * amountCurr;
    }

    /**
     * Returns total price of selling stocks.
     *
     * @param currentPrice - current price of the stock
     * @param amountCurr   - amount of stocks
     * @return sell price
     */
    private Double getTotalSellPrice(Double currentPrice, Double amountCurr) {
        return currentPrice * amountCurr;
    }

}
