package org.project.data.services.interfaces;

import org.project.data.entities.LastProvidedStockEntity;

import java.time.LocalDateTime;

public interface LastProvidedStockService {

    LastProvidedStockEntity find(String symbol);

    void init(String symbol, Long stockId, LocalDateTime stockDate);

    void update(Long stockId, String symbol, LocalDateTime stockDate);

    void save(Long stockId, String symbol, LocalDateTime stockDate);

    boolean exists(String symbol);

}
