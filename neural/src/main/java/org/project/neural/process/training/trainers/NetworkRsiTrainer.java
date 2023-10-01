package org.project.neural.process.training.trainers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.data.entities.indicators.RelativeStrengthIndicatorEntity;
import org.project.data.services.interfaces.indicators.RelativeStrengthIndicatorService;
import org.project.model.Indicators;
import org.project.model.neural.training.TrainParams;
import org.project.neural.process.network.NetworkStore;
import org.project.neural.process.training.NetworkTrainer;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class NetworkRsiTrainer implements NetworkTrainer {

    private final RelativeStrengthIndicatorService relativeStrengthIndicatorService;
    private final NetworkStore networkStore;

    @Override
    public void train(TrainParams params) {
        var symbol = params.getSymbol();
        log.info("Start training RSI network for {}", symbol);
        var network = networkStore.get(Indicators.RSI, params.getSymbol());
        var from = params.getDateFrom();
        var to = params.getDateTo();
        var indicators = relativeStrengthIndicatorService.findAllInPeriod(symbol, from, to);
        var data = prepareData(indicators);
        var answers = getAnswersWithOffset(params.getPrices(), data.size());
        network.train(data, answers, 1000); //TODO: Вынести в параметры кол-во эпох
        networkStore.updateNetwork(Indicators.RSI, symbol, network);
    }

    //Изменить Gain и Loss на их соотношение и что-то еще.
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
