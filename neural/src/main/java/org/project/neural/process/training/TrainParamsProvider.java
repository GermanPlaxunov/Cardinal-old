package org.project.neural.process.training;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.CoreStockEntity;
import org.project.data.services.interfaces.CoreStockService;
import org.project.neural.process.training.training.TrainParams;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class TrainParamsProvider {

    private final CoreStockService coreStockService;

    public TrainParams getTrainParams(String symbol) {
        var cacheDepth = 86400L; //TODO: to params
        var dataset = coreStockService.findCache(symbol, cacheDepth);
        return new TrainParams()
                .setSymbol(symbol)
                .setDateTo(getDateTo(dataset))
                .setDateFrom(getDateFrom(dataset))
                .setPrices(getPrices(dataset));
    }

    private LocalDateTime getDateTo(List<CoreStockEntity> stocks) {
        var size = stocks.size();
        return stocks.stream()
                .map(CoreStockEntity::getDate)
                .skip(size-1)
                .findFirst()
                .orElse(null);
    }

    private LocalDateTime getDateFrom(List<CoreStockEntity> stocks) {
        return stocks.stream()
                .map(CoreStockEntity::getDate)
                .findFirst()
                .orElse(null);
    }

    private List<Double> getPrices(List<CoreStockEntity> stocks) {
        return stocks.stream()
                .map(CoreStockEntity::getClose)
                .toList();
    }

}
