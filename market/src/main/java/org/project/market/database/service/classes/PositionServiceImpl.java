package org.project.market.database.service.classes;

import lombok.RequiredArgsConstructor;
import org.project.market.database.entity.PositionEntity;
import org.project.market.database.repository.PositionRepository;
import org.project.market.database.service.interfaces.PositionService;


@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository repository;
    private final String accountId;

    @Override
    public PositionEntity findOpenPosition(String symbol) {
        return repository.findBySymbolAndAccountIdAndIsOpen(symbol, accountId, true)
                .orElse(null);
    }

    @Override
    public void save(PositionEntity entity) {
        repository.saveAndFlush(entity);
    }
}
