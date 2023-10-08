package org.project.data.services.classes;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.ProcessParamsEntity;
import org.project.data.repositories.ProcessParamsRepository;
import org.project.data.services.interfaces.ProcessParamsService;
import org.project.model.Indicators;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class ProcessParamsServiceImpl implements ProcessParamsService {

    private final ProcessParamsRepository repository;

    @Override
    public void save(ProcessParamsEntity entity) {
        repository.saveAndFlush(entity);
    }

    @Override
    public List<ProcessParamsEntity> findAllByNames(List<String> names) {
        return repository.findAllByNameIn(names)
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public Long getTrainInterval(String symbol, Indicators indicator) {
        var paramName = getTrainIntervalName(symbol, indicator);
        return repository.findByName(paramName)
                .orElse(null)
                .getNumberValue()
                .longValue();
    }

    @Override
    public Long getTrainCacheDepth(String symbol, Indicators indicator) {
        var name = getTrainCacheDepthName(symbol, indicator);
        return repository.findByName(name)
                .orElse(null)
                .getNumberValue()
                .longValue();
    }

    private String getTrainIntervalName(String symbol, Indicators indicator) {
        return symbol.toUpperCase()
                .concat("_")
                .concat(indicator.name().toUpperCase())
                .concat("_TRAIN_INTERVAL");
    }

    private String getTrainCacheDepthName(String symbol, Indicators indicator) {
        return symbol.toUpperCase()
                .concat("_")
                .concat(indicator.name().toUpperCase())
                .concat("_TRAIN_CACHE_DEPTH");
    }
}
