package org.project.data.services.classes;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.CoreStockEntity;
import org.project.data.repositories.CoreStockRepository;
import org.project.data.services.interfaces.CoreStockService;

import java.time.LocalDateTime;
import java.util.List;
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
    public CoreStockEntity findPrevStock(String symbol, LocalDateTime date) {
        return repository.findFirstBySymbolAndDateLessThanOrderByDateDesc(symbol, date)
                .orElse(null);
    }

    @Override
    public List<CoreStockEntity> findCache(String symbol, Long cacheSeconds) {
        var newDate = repository.findFirstBySymbolOrderByDateDesc(symbol)
                .map(CoreStockEntity::getDate)
                .map(date -> date.minusSeconds(cacheSeconds))
                .orElse(null);
        return repository.findAllBySymbolAndDateGreaterThanOrderByDateAsc(symbol, newDate);
    }

    @Override
    public Long count(String symbol) {
        return repository.countBySymbol(symbol);
    }

}
