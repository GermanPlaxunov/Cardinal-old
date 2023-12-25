package org.libra.bragi.services.classes.indicators;

import lombok.RequiredArgsConstructor;
import org.libra.bragi.repositories.indicators.RelativeStrengthIndicatorRepository;
import org.libra.bragi.services.interfaces.indicators.RelativeStrengthIndicatorService;
import org.libra.bragi.entities.indicators.RelativeStrengthEntityDataItem;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class RelativeStrengthIndicatorServiceImpl implements RelativeStrengthIndicatorService {

    private final RelativeStrengthIndicatorRepository repository;

    @Override
    public List<RelativeStrengthEntityDataItem> findAllBySymbol(String symbol) {
        return repository.findAllBySymbol(symbol)
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public void save(RelativeStrengthEntityDataItem entity) {
        repository.saveAndFlush(entity);
    }

    @Override
    public List<RelativeStrengthEntityDataItem> findAllInPeriod(String symbol,
                                                                LocalDateTime from,
                                                                LocalDateTime to) {
        return repository.findAllBySymbolAndDateBetween(symbol, from, to)
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public RelativeStrengthEntityDataItem findLast(String symbol) {
        return repository.findTopBySymbolOrderByDateDesc(symbol)
                .orElse(null);
    }

    @Override
    public List<RelativeStrengthEntityDataItem> findCache(String symbol, Long cacheDepthSeconds) {
        var earliestDate = findLast(symbol)
                .getDate()
                .minusSeconds(cacheDepthSeconds);
        return repository.findAllBySymbolAndDateGreaterThanOrderByDateAsc(symbol, earliestDate)
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }
}
