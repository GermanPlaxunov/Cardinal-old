package org.libra.bragi.services.interfaces;

import org.libra.bragi.entities.CoreStockEntity;

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
