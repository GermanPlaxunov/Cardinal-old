package org.libra.bragi.services.classes;

import lombok.RequiredArgsConstructor;
import org.libra.bragi.repositories.PriceDiffSignalRepository;
import org.libra.bragi.services.interfaces.PriceDiffSignalService;
import org.libra.bragi.entities.PriceDiffSignalEntity;

import java.util.List;

@RequiredArgsConstructor
public class PriceDiffSignalServiceImpl implements PriceDiffSignalService {

    private final PriceDiffSignalRepository repository;

    @Override
    public List<PriceDiffSignalEntity> findAll(String symbol) {
        return repository.findAll();
    }

    @Override
    public Long count(String symbol) {
        return repository.countBySymbol(symbol);
    }

    @Override
    public void save(PriceDiffSignalEntity entity) {
        repository.saveAndFlush(entity);
    }

    @Override
    public PriceDiffSignalEntity findPrevSignal(String symbol) {
        return repository.findTopBySymbolOrderByDateDesc(symbol)
                .orElse(null);
    }
}
