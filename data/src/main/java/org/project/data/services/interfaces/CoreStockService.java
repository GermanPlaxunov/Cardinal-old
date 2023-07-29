package org.project.data.services.interfaces;

import org.project.data.entities.CoreStockEntity;
import org.project.model.MarketStock;

public interface CoreStockService {

    void save(CoreStockEntity entity);

    CoreStockEntity findLastStock(String symbol);

    CoreStockEntity findPrevStock(MarketStock currStock);

}
