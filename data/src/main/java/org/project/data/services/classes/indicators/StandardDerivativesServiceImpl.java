package org.project.data.services.classes.indicators;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.indicators.StandardDerivativesEntity;
import org.project.data.repositories.indicators.StandardDerivativesRepository;
import org.project.data.services.interfaces.indicators.StandardDerivativesService;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class StandardDerivativesServiceImpl implements StandardDerivativesService {

    private final StandardDerivativesRepository repository;

    @Override
    public List<StandardDerivativesEntity> findAllBySymbol(String symbol) {
        return repository.findAllBySymbol(symbol)
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public void save(StandardDerivativesEntity entity) {
        repository.saveAndFlush(entity);
    }
}
