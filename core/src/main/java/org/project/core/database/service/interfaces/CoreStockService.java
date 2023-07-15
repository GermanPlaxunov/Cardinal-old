package org.project.core.database.service.interfaces;

import org.project.core.database.entity.CoreStockEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface CoreStockService {

    List<CoreStockEntity> findAllInPerion(String symbol, LocalDateTime from, LocalDateTime to);

    void save(CoreStockEntity entity);

    CoreStockEntity findLastStock(String symbol);

    int countBySymbol(String symbol);

}
