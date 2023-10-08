package org.project.data.services.classes.indicators;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.indicators.SimpleMovingAverageEntity;
import org.project.data.repositories.indicators.SimpleMovingAverageRepository;
import org.project.data.services.interfaces.indicators.SimpleMovingAverageService;

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
