package org.cardinal.data.services.interfaces;

import org.cardinal.data.entities.CoreStockEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface CoreStockService {

    void save(CoreStockEntity entity);

    List<CoreStockEntity> findCache(String symbol, Long cacheSeconds);

    CoreStockEntity findLast(String symbol);

    CoreStockEntity findPrevious(String symbol, LocalDateTime lastDate, Long stepBackSeconds);

    CoreStockEntity findById(Long id);

    boolean checkCacheExists(String symbol);

}
