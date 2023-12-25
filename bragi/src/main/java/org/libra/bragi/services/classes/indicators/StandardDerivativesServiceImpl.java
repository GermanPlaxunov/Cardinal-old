package org.libra.bragi.services.classes.indicators;

import lombok.RequiredArgsConstructor;
import org.libra.bragi.repositories.indicators.StandardDerivativesRepository;
import org.libra.bragi.services.interfaces.indicators.StandardDerivativesService;
import org.libra.bragi.entities.indicators.StandardDerivativesEntity;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class StandardDerivativesServiceImpl implements StandardDerivativesService {

    private final StandardDerivativesRepository repository;

    @Override
    public List<StandardDerivativesEntity> findAllBySymbol(String symbol) {
        return repository.findAllBySymbol(symbol)
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public void save(StandardDerivativesEntity entity) {
        repository.saveAndFlush(entity);
    }

    @Override
    public StandardDerivativesEntity findLast(String symbol) {
        return repository.findTopBySymbolOrderByDateDesc(symbol)
                .orElse(null);
    }

    @Override
    public List<StandardDerivativesEntity> findCache(String symbol, Long cacheDepthSeconds) {
        var earliestDate = findLast(symbol)
                .getDate()
                .minusSeconds(cacheDepthSeconds);
        return repository.findAllBySymbolAndDateGreaterThanOrderByDateAsc(symbol, earliestDate)
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }
}
