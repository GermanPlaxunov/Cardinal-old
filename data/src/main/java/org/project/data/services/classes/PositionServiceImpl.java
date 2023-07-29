package org.project.data.services.classes;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.PositionEntity;
import org.project.data.repositories.PositionRepository;
import org.project.data.services.interfaces.PositionService;

import java.util.Objects;

@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;
    private final String accountId;

    @Override
    public PositionEntity findOpenPosition(String symbol) {
        return positionRepository.findBySymbolAndAccountIdAndIsOpen(symbol, accountId, true)
                .stream()
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    @Override
    public PositionEntity findLatestClosedPosition(String symbol) {
        return positionRepository.findFirstBySymbolAndCloseDateIsNotNullOrderByCloseDateDesc(symbol)
                .orElse(null);
    }

    @Override
    public void save(PositionEntity entity) {
        positionRepository.saveAndFlush(entity);
    }
}
