package org.project.data.services.interfaces;

import org.project.data.entities.MarketStockEntity;

import java.time.LocalDateTime;

public interface MarketStockService {

    MarketStockEntity findNext(String stockName, LocalDateTime lastStockDate);

    MarketStockEntity findById(Long id);

}
