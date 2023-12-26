package org.libra.data.services.classes;

import lombok.RequiredArgsConstructor;
import org.libra.data.repositories.PositionRepository;
import org.libra.data.services.interfaces.PositionService;
import org.libra.data.entities.PositionEntity;

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

    @Override
    public boolean ifOpenPosition(String symbol) {
        return positionRepository.countBySymbolAndIsOpen(symbol, true) > 0;
    }

}
