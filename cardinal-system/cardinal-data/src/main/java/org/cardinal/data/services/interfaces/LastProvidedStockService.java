package org.cardinal.data.services.interfaces;

import org.cardinal.data.entities.LastProvidedStockEntity;

import java.time.LocalDateTime;

public interface LastProvidedStockService {

    LastProvidedStockEntity find(String symbol);

    void update(Long stockId, String symbol, LocalDateTime stockDate);

    void save(Long stockId, String symbol, LocalDateTime stockDate);

    boolean exists(String symbol);

}
