package org.libra.bragi.services.classes.indicators;

import lombok.RequiredArgsConstructor;
import org.libra.bragi.repositories.indicators.SimpleMovingAverageRepository;
import org.libra.bragi.services.interfaces.indicators.SimpleMovingAverageService;
import org.libra.bragi.entities.indicators.SimpleMovingAverageEntity;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class SimpleMovingAverageServiceImpl implements SimpleMovingAverageService {

    private final SimpleMovingAverageRepository repository;

    @Override
    public List<SimpleMovingAverageEntity> findAllBySymbol(String symbol) {
        return repository.findAllBySymbol(symbol)
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public void save(SimpleMovingAverageEntity entity) {
        repository.saveAndFlush(entity);
    }

    @Override
    public SimpleMovingAverageEntity findLast(String symbol) {
        return repository.findTopBySymbolOrderByDateDesc(symbol)
                .orElse(null);
    }

    @Override
    public List<SimpleMovingAverageEntity> findCache(String symbol, Long cacheDepthSeconds) {
        var earliestDate = findLast(symbol)
                .getDate()
                .minusSeconds(cacheDepthSeconds);
        return repository.findAllBySymbolAndDateGreaterThanOrderByDateAsc(symbol, earliestDate)
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }
}
