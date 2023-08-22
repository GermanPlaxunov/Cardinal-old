package org.project.data.services.interfaces;

import org.project.data.entities.CoreStockEntity;
import org.project.model.MarketStock;

import java.time.LocalDateTime;
import java.util.List;

public interface CoreStockService {

    void save(CoreStockEntity entity);

    CoreStockEntity findLastStock(String symbol);

    CoreStockEntity findPrevStock(String symbol, LocalDateTime date);

    List<CoreStockEntity> findCache(String symbol, Long cacheSeconds);

    Long count(String symbol);
}
