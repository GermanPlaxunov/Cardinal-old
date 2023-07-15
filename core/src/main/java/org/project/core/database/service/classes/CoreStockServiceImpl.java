package org.project.core.database.service.classes;

import lombok.RequiredArgsConstructor;
import org.project.core.database.entity.CoreStockEntity;
import org.project.core.database.repository.CoreStockRepository;
import org.project.core.database.service.interfaces.CoreStockService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class CoreStockServiceImpl implements CoreStockService {

    private final CoreStockRepository repository;

    @Override
    public List<CoreStockEntity> findAllInPerion(String symbol, LocalDateTime from, LocalDateTime to) {
        return repository.findAllBySymbolAndDateBetween(symbol, from, to);
    }

    @Override
    public void save(CoreStockEntity entity) {
        repository.save(entity);
    }

    @Override
    public CoreStockEntity findLastStock(String symbol) {
        return repository.findFirstBySymbolOrderByDateDesc(symbol)
                .filter(Objects::nonNull)
                .orElse(null);
    }
}
