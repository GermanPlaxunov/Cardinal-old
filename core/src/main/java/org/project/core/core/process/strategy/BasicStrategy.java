package org.project.core.core.process.strategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.core.core.process.strategy.basic.FixProfitProvider;
import org.project.core.core.process.strategy.basic.StopLossProvider;
import org.project.core.core.process.vars.BasicStrategyResult;
import org.project.data.entities.PositionEntity;
import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.PositionService;
import org.project.model.MarketStock;

@Slf4j
@RequiredArgsConstructor
public class BasicStrategy {

    private final FixProfitProvider fixProfitProvider;
    private final StopLossProvider stopLossProvider;
    private final CoreStockService coreStockService;
    private final PositionService positionService;

    public BasicStrategyResult startProcess(MarketStock stock) {
        var symbol = stock.getSymbol();
        BasicStrategyResult result = null;
        var openPosition = positionService.findOpenPosition(symbol);
        if (openPosition != null) {
            result = verifyOpenPosition(openPosition, stock);
        } else {
            // Тут должна быть логика открытия позиции
        }
        return result;
    }

    private BasicStrategyResult verifyOpenPosition(PositionEntity position, MarketStock stock) {
        var symbol = position.getSymbol();
        var closeFlg = getClosePositionFlg(position.getOpenPrice(), stock.getClose());
        return new BasicStrategyResult()
                .setSymbol(symbol)
                .setAmount(1.0)
                .setFixProfitPrice(fixProfitProvider.getFixProfitPrice(position.getOpenPrice()))
                .setStopLossPrice(stopLossProvider.getStopLossPrice(position.getOpenPrice()))
                .setCurrentPrice(stock.getClose())
                .setOpenPositionsCount(1)
                .setClosePositionSignal(closeFlg);
    }

    private boolean getClosePositionFlg(Double openPositionPrice, Double currentPrice) {
        var sl = stopLossProvider.checkStopLoss(openPositionPrice, currentPrice);
        var fp = fixProfitProvider.checkFixProfit(openPositionPrice, currentPrice);
        return sl || fp;
    }

}