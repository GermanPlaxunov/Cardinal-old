package org.libra.bragi.services.classes;

import lombok.RequiredArgsConstructor;
import org.libra.bragi.repositories.MarketStockRepository;
import org.libra.bragi.services.interfaces.MarketStockService;
import org.libra.bragi.entities.MarketStockEntity;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class MarketStockServiceImpl implements MarketStockService {

    private final MarketStockRepository marketStockRepository;

    @Override
    public MarketStockEntity findFirst(String symbol) {
        return marketStockRepository.findTopBySymbolOrderByDateAsc(symbol)
                .orElse(null);
    }

    @Override
    public MarketStockEntity findNext(String symbol, LocalDateTime lastStockDate) {
        return marketStockRepository.findTopBySymbolAndDateGreaterThanOrderByDateAsc(symbol, lastStockDate)
                .orElse(null);
    }

    @Override
    public MarketStockEntity findById(Long id) {
        return marketStockRepository.findById(id)
                .orElse(null);
    }
}
