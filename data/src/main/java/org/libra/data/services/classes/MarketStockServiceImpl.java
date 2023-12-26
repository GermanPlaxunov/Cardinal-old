package org.libra.data.services.classes;

import lombok.RequiredArgsConstructor;
import org.libra.data.repositories.MarketStockRepository;
import org.libra.data.services.interfaces.MarketStockService;
import org.libra.data.entities.MarketStockEntity;

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
