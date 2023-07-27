package org.project.data.services.classes;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.CoreStockEntity;
import org.project.data.repositories.CoreStockRepository;
import org.project.data.services.interfaces.CoreStockService;
import org.project.model.MarketStock;

import java.util.Objects;

@RequiredArgsConstructor
public class CoreStockServiceImpl implements CoreStockService {

    private final CoreStockRepository repository;

    @Override
    public void save(CoreStockEntity entity) {
        repository.saveAndFlush(entity);
    }

    @Override
    public CoreStockEntity findLastStock(String symbol) {
        return repository.findFirstBySymbolOrderByDateDesc(symbol)
                .filter(Objects::nonNull)
                .orElse(null);
    }

    @Override
    public CoreStockEntity findPrevStock(MarketStock currStock) {
        var symbol = currStock.getSymbol();
        var date = currStock.getDate();
        return repository.findFirstBySymbolAndDateLessThanOrderByDateDesc(symbol, date)
                .orElse(null);
    }

}
