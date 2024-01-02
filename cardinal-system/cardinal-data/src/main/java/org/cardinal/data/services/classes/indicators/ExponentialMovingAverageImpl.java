package org.cardinal.data.services.classes.indicators;

import lombok.RequiredArgsConstructor;
import org.cardinal.data.repositories.indicators.ExponentialMovingAverageRepository;
import org.cardinal.data.services.interfaces.indicators.ExponentialMovingAverageService;
import org.cardinal.data.entities.indicators.ExponentialMovingAverageEntity;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class ExponentialMovingAverageImpl implements ExponentialMovingAverageService {

    private final ExponentialMovingAverageRepository repository;

    @Override
    public List<ExponentialMovingAverageEntity> findAllBySymbol(String symbol) {
        return repository.findAllBySymbol(symbol)
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public void save(ExponentialMovingAverageEntity entity) {
        repository.saveAndFlush(entity);
    }

    @Override
    public ExponentialMovingAverageEntity findLast(String symbol) {
        return repository.findTopBySymbolOrderByDateDesc(symbol)
                .orElse(null);
    }

    @Override
    public List<ExponentialMovingAverageEntity> findCache(String symbol, Long cacheDepthSeconds) {
        var earliestDate = findLast(symbol)
                .getDate()
                .minusSeconds(cacheDepthSeconds);
        return repository.findAllBySymbolAndDateGreaterThanOrderByDateAsc(symbol, earliestDate)
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }
}
