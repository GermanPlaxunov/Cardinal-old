package org.libra.data.services.classes;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.libra.data.repositories.LastProvidedStockRepository;
import org.libra.data.entities.LastProvidedStockEntity;
import org.libra.data.services.interfaces.LastProvidedStockService;

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
