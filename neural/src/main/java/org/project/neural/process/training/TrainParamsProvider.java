package org.project.neural.process.training;

import lombok.RequiredArgsConstructor;
import org.libra.bragi.cache.CacheDepthProvider;
import org.libra.bragi.entities.CoreStockEntity;
import org.libra.bragi.services.interfaces.CoreStockService;
import org.project.neural.process.training.training.TrainParams;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class TrainParamsProvider {

    private final CacheDepthProvider cacheDepthProvider;
    private final CoreStockService coreStockService;

    public TrainParams getTrainParams(String symbol) {
        var cacheDepths = cacheDepthProvider.getAllIndicatorsCacheDepths(symbol);
        var maxDepth = cacheDepths.getMaxDepth();
        var stocks = coreStockService.findCache(symbol, maxDepth);
        return new TrainParams()
                .setSymbol(symbol)
                .setDateTo(getDateTo(stocks))//TODO: Can be removed
                .setDateFrom(getDateFrom(stocks))//TODO: Can be removed
                .setPrices(getPrices(stocks))//TODO: can be removed
                .setStocks(stocks)
                .setEpochs(1000L);//TODO: Can be removed.
    }

    private LocalDateTime getDateTo(List<CoreStockEntity> stocks) {
        var size = stocks.size();
        return stocks.stream()
                .map(CoreStockEntity::getDate)
                .skip(size - 1)
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
