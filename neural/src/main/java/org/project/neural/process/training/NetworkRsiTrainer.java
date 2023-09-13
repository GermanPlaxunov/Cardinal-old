package org.project.neural.process.training;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.indicators.RelativeStrengthIndicatorEntity;
import org.project.data.services.interfaces.indicators.RelativeStrengthIndicatorService;
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
        network.train(data, answers, 10000);
        networkStore.updateNetwork(TYPE, symbol, network);
    }

    private List<List<Double>> prepareData(List<RelativeStrengthIndicatorEntity> indicators) {
        return indicators.stream()
                .map(rsi -> List.of(rsi.getGainSumm(), rsi.getLossSumm()))
                .toList();
    }

    /**
     * Should return the price change over each period.
     *
     * @param prices     - the list of stock`s price.
     * @param sizeOfData - the size of Rsi list.
     */
    private List<Double> getAnswersWithOffset(List<Double> prices, Integer sizeOfData) {
        var offset = prices.size() - sizeOfData;
        var offsetPrices = prices.stream()
                .skip(offset - 1)
                .toList();
        var changes = new ArrayList<Double>();
        for (var i = 1; i < offsetPrices.size(); i++) {
            changes.add(offsetPrices.get(i) - offsetPrices.get(i - 1));
        }
        return changes;
    }

}
