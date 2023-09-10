package org.project.neural.process.training;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.CoreStockEntity;
import org.project.data.entities.indicators.RelativeStrengthIndicatorEntity;
import org.project.data.entities.neural.NeuralNetworkEntity;
import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.indicators.RelativeStrengthIndicatorService;
import org.project.data.services.interfaces.neural.NeuralNetworkService;
import org.project.model.neural.training.TrainParams;
import org.project.neural.process.network.NetworkStore;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class NetworkRsiTrainer {
    private final String TYPE = "RSI";
    private final RelativeStrengthIndicatorService relativeStrengthIndicatorService;
    private final NetworkStore networkStore;

    public void train(TrainParams params) {
        var network = networkStore.get(TYPE, params.getSymbol());
        var symbol = params.getSymbol();
        var from = params.getDateFrom();
        var to = params.getDateTo();
        var indicators = relativeStrengthIndicatorService.findAllInPeriod(symbol, from, to);
        var data = prepareData(indicators);
        var answers = getAnswersWithOffset(params.getPrices(), data.size());
        network.train(data, answers, 5000);
        networkStore.updateNetwork(TYPE, symbol, network);
    }

    private List<List<Double>> prepareData(List<RelativeStrengthIndicatorEntity> indicators) {
        return indicators.stream()
                .map(rsi -> List.of(rsi.getGainSumm(), rsi.getLossSumm()))
                .toList();
    }

    /**
     * In purpose to get the affect of present values
     * on price in the future.
     *
     * @param prices - the list of stock`s price.
     * @param sizeOfData - the size of Rsi list.
     */
    private List<Double> getAnswersWithOffset(List<Double> prices, Integer sizeOfData) {
        var offset = prices.size() - sizeOfData;
        return prices.stream()
                .skip(offset)
                .toList();
    }

}
