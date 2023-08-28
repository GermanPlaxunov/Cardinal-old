package org.project.neural.process.training;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.CoreStockEntity;
import org.project.data.services.interfaces.CoreStockService;
import org.project.model.neural.training.TrainParams;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class TrainParamsProvider {

    private final CoreStockService coreStockService;

    public TrainParams getTrainParams(String symbol) {
        var dataset = coreStockService.findCache(symbol, 600L);
        return new TrainParams()
                .setSymbol(symbol)
                .setDateTo(getDateTo(dataset))
                .setDateFrom(getDateFrom(dataset));
    }

    private LocalDateTime getDateTo(List<CoreStockEntity> stocks) {
        return stocks.stream()
                .map(CoreStockEntity::getDate)
                .findFirst()
                .orElse(null);
    }

    private LocalDateTime getDateFrom(List<CoreStockEntity> stocks) {
        var size = stocks.size();
        return stocks.stream()
                .skip(size - 1)
                .map(CoreStockEntity::getDate)
                .findFirst()
                .orElse(null);
    }

}
