package org.project.market.database.service.classes;

import lombok.RequiredArgsConstructor;
import org.project.market.database.entity.StockEntity;
import org.project.market.database.repository.StockRepository;
import org.project.market.database.service.interfaces.StockService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Override
    public List<StockEntity> findAll() {
        return stockRepository.findAll();
    }

    @Override
    public void save(String symbol, Double open,
                     Double close, Double high, Double low,
                     Double volumeCurr, Double volumeUsd, LocalDateTime date) {
        var entity = new StockEntity()
                .setSymbol(symbol)
                .setOpen(open)
                .setClose(close)
                .setHigh(high)
                .setLow(low)
                .setVolumeCurr(volumeCurr)
                .setVolumeUsd(volumeUsd)
                .setDate(date);
        stockRepository.saveAndFlush(entity);
    }

    @Override
    public void saveAll(List<StockEntity> entities) {
        stockRepository.saveAllAndFlush(entities);
    }

    @Override
    public StockEntity findNext(String stockName, LocalDateTime lastStockDate) {
        return stockRepository.findFirstBySymbolAndDateGreaterThanOrderByDateAsc(stockName, lastStockDate)
                .filter(Objects::nonNull)
                .orElse(null);
    }

    @Override
    public StockEntity findById(Long id) {
        return stockRepository.findById(id)
                .orElse(null);
    }
}
