package org.project.market.database.service.interfaces;

import org.project.market.database.entity.LastProvidedStockEntity;

public interface LastProvidedStockService {

    LastProvidedStockEntity find(String symbol);

    void update(Long stockId, String symbol);

}
