package org.cardinal.core.process.strategy;

import lombok.RequiredArgsConstructor;
import org.cardinal.core.decision.BuyAmountCurrencyProcessor;
import org.cardinal.data.entities.PositionEntity;
import org.cardinal.data.services.interfaces.PositionService;
import org.cardinal.data.services.interfaces.ProcessParamsService;
import org.cardinal.model.CoreStock;
import org.cardinal.model.ProcessVars;
import org.cardinal.model.strategy.MainStrategyResult;

@RequiredArgsConstructor
public class MainStrategy {

    private final BuyAmountCurrencyProcessor buyAmountCurrencyProcessor;
    private final ProcessParamsService processParamsService;
    private final PositionService positionService;

    /**
     * Checks if there is any open positions. If there is
     * checks if it should be closed, otherwise checks for
     * closing existing position. Without neural algorithms.
     *
     * @param processVars - process data
     */
    public void launchStrategy(ProcessVars<CoreStock> processVars) {
        var symbol = processVars.getSymbol();
        if (positionService.ifOpenPosition(symbol)) {
            ifCurrentPositionShouldBeClosed(processVars);
        } else {
            ifNewPositionShouldBeOpened(processVars);
        }
    }

    /**
     * Launches analyze about opening new position.
     *
     * @param processVars - process data
     */
    private void ifNewPositionShouldBeOpened(ProcessVars<CoreStock> processVars) {
        var result = new MainStrategyResult();
        var symbol = processVars.getSymbol();
        var lastStock = processVars.getLastProvidedStock();
        var position = positionService.findLatestClosedPosition(symbol);
        var currencyAmount = buyAmountCurrencyProcessor.getBuyAmountCurrency(processVars);
        var forceOpenNewPosition = ifEnoughTimeHasPassedFromClosingPreviousPosition(symbol, lastStock, position);
        if (forceOpenNewPosition) {
            result.setSymbol(symbol)
                    .setShouldNewPositionBeOpen(true)
                    .setAmount(currencyAmount);
        } else {
            //TODO: Additional logic of checking the price change
            result.setSymbol(symbol)
                    .setShouldNewPositionBeOpen(true)
                    .setAmount(currencyAmount);
        }
        processVars.setMainStrategyResult(result);
    }

    /**
     * Launches analyze about closing current position.
     *
     * @param processVars - process data
     */
    private void ifCurrentPositionShouldBeClosed(ProcessVars<CoreStock> processVars) {
        var result = new MainStrategyResult();
        var symbol = processVars.getSymbol();
        var position = positionService.findOpenPosition(symbol);
        result.setSymbol(symbol); //TODO: L-79
        processVars.setMainStrategyResult(result);
    }

    /**
     * Checks if enough time has passed from closing
     * previous position. If it is than new position will
     * be opened without any additional checks.
     *
     * @param lastStock - last obtained stock point
     * @param position  - position entity
     * @return true/false
     */
    private boolean ifEnoughTimeHasPassedFromClosingPreviousPosition(String symbol, CoreStock lastStock, PositionEntity position) {
        var closeDate = position.getCloseDate();
        var currentDate = lastStock.getDate();
        var interval = processParamsService.getMaxIntervalInSecondsOfOpeningNewPosition(symbol);
        return closeDate.plusSeconds(interval)
                .isBefore(currentDate);
    }

}
