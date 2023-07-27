package org.project.data.services.classes;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.MarketStockEntity;
import org.project.data.repositories.MarketStockRepository;
import org.project.data.services.interfaces.MarketStockService;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class MarketStockServiceImpl implements MarketStockService {

    private final MarketStockRepository marketStockRepository;

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
