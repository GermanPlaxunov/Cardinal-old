package org.project.data.services.classes.indicators;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.indicators.RelativeStrengthIndicatorEntity;
import org.project.data.repositories.indicators.RelativeStrengthIndicatorRepository;
import org.project.data.services.interfaces.indicators.RelativeStrengthIndicatorService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class RelativeStrengthIndicatorServiceImpl implements RelativeStrengthIndicatorService {

    private final RelativeStrengthIndicatorRepository repository;

    @Override
    public List<RelativeStrengthIndicatorEntity> findAllBySymbol(String symbol) {
        return repository.findAllBySymbol(symbol)
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public void save(RelativeStrengthIndicatorEntity entity) {
        repository.saveAndFlush(entity);
    }

    @Override
    public List<RelativeStrengthIndicatorEntity> findAllInPeriod(String symbol,
                                                                 LocalDateTime from,
                                                                 LocalDateTime to) {
        return repository.findAllBySymbolAndDateBetween(symbol, from, to)
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public RelativeStrengthIndicatorEntity findLast(String symbol) {
        return repository.findTopBySymbolOrderByDateDesc(symbol)
                .orElse(null);
    }
}
