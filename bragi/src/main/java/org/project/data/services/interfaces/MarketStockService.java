package org.project.data.services.interfaces;

import org.project.data.entities.MarketStockEntity;

import java.time.LocalDateTime;

public interface MarketStockService {

    MarketStockEntity findFirst(String symbol);

    MarketStockEntity findNext(String symbol, LocalDateTime lastStockDate);

    MarketStockEntity findById(Long id);

}
