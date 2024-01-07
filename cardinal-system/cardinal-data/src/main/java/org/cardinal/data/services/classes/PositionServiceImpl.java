package org.cardinal.data.services.classes;

import lombok.RequiredArgsConstructor;
import org.cardinal.data.entities.PositionEntity;
import org.cardinal.data.repositories.PositionRepository;
import org.cardinal.data.services.interfaces.PositionService;

import java.util.Objects;

@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;
    private final String accountId;

    @Override
    public PositionEntity findOpenPosition(String figi) {
        return positionRepository.findByFigiAndAccountIdAndIsOpen(figi, accountId, true)
                .stream()
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    @Override
    public PositionEntity findLatestClosedPosition(String figi) {
        return positionRepository.findFirstByFigiAndCloseDateIsNotNullOrderByCloseDateDesc(figi)
                .orElse(null);
    }

    @Override
    public void save(PositionEntity entity) {
        positionRepository.saveAndFlush(entity);
    }

    @Override
    public boolean ifOpenPosition(String figi) {
        return positionRepository.countByFigiAndIsOpen(figi, true) > 0;
    }

}
