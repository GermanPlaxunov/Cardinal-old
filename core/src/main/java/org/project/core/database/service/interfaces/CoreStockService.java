package org.project.core.database.service.interfaces;

import org.project.core.database.entity.CoreStockEntity;
import org.project.model.MarketStock;

import java.time.LocalDateTime;
import java.util.List;

public interface CoreStockService {

    void save(CoreStockEntity entity);

    CoreStockEntity findLastStock(String symbol);

    CoreStockEntity findPrevStock(MarketStock currStock);

}
