package org.cardinal.data.services.classes;

import lombok.RequiredArgsConstructor;
import org.cardinal.data.repositories.PriceDiffSignalRepository;
import org.cardinal.data.services.interfaces.PriceDiffSignalService;
import org.cardinal.data.entities.PriceDiffSignalEntity;

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
