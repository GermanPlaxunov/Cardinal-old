package org.libra.data.services.classes.indicators;

import lombok.RequiredArgsConstructor;
import org.libra.data.services.interfaces.indicators.BollingerBandsService;
import org.libra.data.entities.indicators.BollingerBandsEntity;
import org.libra.data.repositories.indicators.BollingerBandsRepository;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class BollingerBandsServiceImpl implements BollingerBandsService {

    private final BollingerBandsRepository repository;

    @Override
    public List<BollingerBandsEntity> findAllBySymbol(String symbol) {
        return repository.findAllBySymbol(symbol)
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public void save(BollingerBandsEntity entity) {
        repository.saveAndFlush(entity);
    }

    @Override
    public BollingerBandsEntity findLast(String symbol) {
        return repository.findTopBySymbolOrderByDateDesc(symbol)
                .orElse(null);
    }

    @Override
    public List<BollingerBandsEntity> findCache(String symbol, Long cacheDepthSeconds) {
        var earliestDate = findLast(symbol)
                .getDate()
                .minusSeconds(cacheDepthSeconds);
        return repository.findAllBySymbolAndDateGreaterThanOrderByDateAsc(symbol, earliestDate)
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }
}
