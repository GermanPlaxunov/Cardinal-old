package org.project.market.database.service.interfaces;

import org.project.market.database.entity.LastProvidedStockEntity;

import java.time.LocalDateTime;

public interface LastProvidedStockService {

    LastProvidedStockEntity find(String symbol);

    void update(Long stockId, String symbol, LocalDateTime stockDate);

    void save(Long stockId, String symbol, LocalDateTime stockDate);

}
