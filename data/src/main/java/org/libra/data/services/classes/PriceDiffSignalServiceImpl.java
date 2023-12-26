package org.libra.data.services.classes;

import lombok.RequiredArgsConstructor;
import org.libra.data.repositories.PriceDiffSignalRepository;
import org.libra.data.services.interfaces.PriceDiffSignalService;
import org.libra.data.entities.PriceDiffSignalEntity;

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
