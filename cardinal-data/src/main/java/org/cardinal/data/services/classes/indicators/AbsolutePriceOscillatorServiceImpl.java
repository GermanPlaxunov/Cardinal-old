package org.cardinal.data.services.classes.indicators;

import lombok.RequiredArgsConstructor;
import org.cardinal.data.repositories.indicators.AbsolutePriceOscillatorRepository;
import org.cardinal.data.services.interfaces.indicators.AbsolutePriceOscillatorService;
import org.cardinal.data.entities.indicators.AbsolutePriceOscillatorEntity;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class AbsolutePriceOscillatorServiceImpl implements AbsolutePriceOscillatorService {

    private final AbsolutePriceOscillatorRepository repository;

    @Override
    public List<AbsolutePriceOscillatorEntity> findAllBySymbol(String symbol) {
        return repository.findAllBySymbol(symbol)
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public void save(AbsolutePriceOscillatorEntity entity) {
        repository.saveAndFlush(entity);
    }

    @Override
    public AbsolutePriceOscillatorEntity findLast(String symbol) {
        return repository.findTopBySymbolOrderByDateDesc(symbol)
                .orElse(null);
    }

    @Override
    public List<AbsolutePriceOscillatorEntity> findCache(String symbol, Long cacheDepthSeconds) {
        var earliestDate = findLast(symbol)
                .getDate()
                .minusSeconds(cacheDepthSeconds);
        return repository.findAllBySymbolAndDateGreaterThanOrderByDateAsc(symbol, earliestDate)
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }
}
