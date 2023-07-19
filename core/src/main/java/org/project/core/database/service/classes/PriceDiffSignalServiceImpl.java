package org.project.core.database.service.classes;

import lombok.RequiredArgsConstructor;
import org.project.core.database.entity.PriceDiffSignalEntity;
import org.project.core.database.repository.PriceDiffSignalRepository;
import org.project.core.database.service.interfaces.PriceDiffSignalService;
import org.project.model.MarketStock;

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
        repository.save(entity);
    }

    @Override
    public PriceDiffSignalEntity findPrevSignal(MarketStock stock) {
        var symbol = stock.getSymbol();
        var date = stock.getDate();
        return repository.findFirstBySymbolAndDateLessThanOrderByDateDesc(symbol, date)
                .orElse(null);
    }
}
