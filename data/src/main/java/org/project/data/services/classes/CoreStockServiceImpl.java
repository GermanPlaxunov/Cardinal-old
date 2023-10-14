package org.project.data.services.classes;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.CoreStockEntity;
import org.project.data.repositories.CoreStockRepository;
import org.project.data.services.interfaces.CoreStockService;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class CoreStockServiceImpl implements CoreStockService {

    private final CoreStockRepository repository;

    @Override
    public void save(CoreStockEntity entity) {
        repository.saveAndFlush(entity);
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
    public CoreStockEntity findLast(String symbol) {
        return repository.findFirstBySymbolOrderByDateDesc(symbol)
                .orElse(null);
    }

    @Override
    public CoreStockEntity findPrevious(String symbol, LocalDateTime lastDate, Long stepBackSeconds) {
        var date = lastDate.minusSeconds(stepBackSeconds);
        return repository.findFirstBySymbolAndDateLessThanOrderByDateDesc(symbol, date)
                .orElse(null);
    }

}
