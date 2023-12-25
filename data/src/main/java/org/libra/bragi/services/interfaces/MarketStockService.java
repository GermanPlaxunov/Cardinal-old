package org.libra.bragi.services.interfaces;

import org.libra.bragi.entities.MarketStockEntity;

import java.time.LocalDateTime;

public interface MarketStockService {

    MarketStockEntity findFirst(String symbol);

    MarketStockEntity findNext(String symbol, LocalDateTime lastStockDate);

    MarketStockEntity findById(Long id);

}
