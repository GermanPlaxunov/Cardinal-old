package org.project.neural.process.training;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.CoreStockEntity;
import org.project.data.entities.indicators.RelativeStrengthIndicatorEntity;
import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.indicators.RelativeStrengthIndicatorService;
import org.project.model.neural.training.TrainParams;
import org.project.neural.process.network.NetworkProvider;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class NetworkRsiTrainer {

    private final RelativeStrengthIndicatorService relativeStrengthIndicatorService;
    private final CoreStockService coreStockService;
    private final NetworkProvider networkProvider;

    public void train(TrainParams params) {
        var networkName = "RSI->".concat(params.getSymbol());
        var network = networkProvider.get(networkName);
        var symbol = params.getSymbol();
        var from = params.getDateFrom();
        var to = params.getDateTo();
        var stocks = coreStockService.findAllInPeriod(symbol, from, to);
        var indicators = relativeStrengthIndicatorService.findAllInPeriod(symbol, from, to);
        var data = prepareData(indicators);
        var answers = getAnswersWithOffset(stocks, 1);
        network.train(data, answers, 1000);
    }

    private List<List<Double>> prepareData(List<RelativeStrengthIndicatorEntity> indicators) {
        var result = new ArrayList<List<Double>>();
        for (var indicator : indicators) {
            result.add(List.of(indicator.getGainSumm(), indicator.getLossSumm()));
        }
        result.remove(result.size() - 1); /* To avoid index out of bound because of offset on answers */
        return result;
    }

    /**
     * In purpose to get the affect of present values
     * on price in the future.
     *
     * @param stocks
     */
    private List<Double> getAnswersWithOffset(List<CoreStockEntity> stocks, Integer offsetSize) {
        stocks.remove(offsetSize);
        return stocks.stream()
                .map(CoreStockEntity::getClose)
                .toList();
    }

}
