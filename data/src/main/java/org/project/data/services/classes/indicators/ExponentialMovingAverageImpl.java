package org.project.data.services.classes.indicators;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.indicators.ExponentialMovingAverageEntity;
import org.project.data.repositories.indicators.ExponentialMovingAverageRepository;
import org.project.data.services.interfaces.indicators.ExponentialMovingAverageService;

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
}
