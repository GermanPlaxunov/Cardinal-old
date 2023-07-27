package org.project.data.services.classes;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.PositionEntity;
import org.project.data.repositories.PositionRepository;
import org.project.data.services.interfaces.PositionService;

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
