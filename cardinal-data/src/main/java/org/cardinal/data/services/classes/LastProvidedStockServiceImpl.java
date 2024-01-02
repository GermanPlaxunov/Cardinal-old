package org.cardinal.data.services.classes;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.cardinal.data.repositories.LastProvidedStockRepository;
import org.cardinal.data.services.interfaces.LastProvidedStockService;
import org.cardinal.data.entities.LastProvidedStockEntity;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class LastProvidedStockServiceImpl implements LastProvidedStockService {

    private final LastProvidedStockRepository lastProvidedStockRepository;

    @Override
    @Transactional
    public LastProvidedStockEntity find(String symbol) {
        return lastProvidedStockRepository.findBySymbol(symbol)
                .orElse(null);
    }

    @Override
    @Transactional
    public void update(Long stockId, String symbol, LocalDateTime stockDate) {
        lastProvidedStockRepository.update(stockId, stockDate, symbol);
    }

    @Override
    @Transactional
    public void save(Long stockId, String symbol, LocalDateTime stockDate) {
        var entity = new LastProvidedStockEntity()
                .setStockId(stockId)
                .setSymbol(symbol)
                .setStockDate(stockDate);
        lastProvidedStockRepository.saveAndFlush(entity);
    }

    @Override
    public boolean exists(String symbol) {
        return lastProvidedStockRepository.existsBySymbol(symbol);
    }

}
