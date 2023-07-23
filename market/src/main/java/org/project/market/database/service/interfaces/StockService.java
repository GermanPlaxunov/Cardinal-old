package org.project.market.database.service.interfaces;

import org.project.market.database.entity.StockEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface StockService {

    List<StockEntity> findAll();

    void save(String symbol, Double open,
              Double close, Double high, Double low,
              Double volumeCurr, Double volumeUsd, LocalDateTime date);

    void saveAll(List<StockEntity> entities);

    StockEntity findNext(String stockName, LocalDateTime lastStockDate);

    StockEntity findById(Long id);

}
