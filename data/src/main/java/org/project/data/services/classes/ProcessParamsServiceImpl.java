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
    public Long getProcessCacheDepth(String symbol) {
        return repository.findByName(symbol.toUpperCase().concat("_CACHE_DEPTH"))
                .map(ProcessParamsEntity::getNumberValue)
                .map(Double::longValue)
                .orElse(0L);
    }

    @Override
    public Long getTrainInterval(String symbol, Indicators indicator) {
        var paramName = getTrainIntervalName(symbol, indicator);
        return repository.findByName(paramName)
                .map(ProcessParamsEntity::getNumberValue)
                .map(Double::longValue)
                .orElse(0L);
    }

    @Override
    public Long getTrainCacheDepth(String symbol, Indicators indicator) {
        var name = getTrainCacheDepthName(symbol, indicator);
        return repository.findByName(name)
                .map(ProcessParamsEntity::getNumberValue)
                .map(Double::longValue)
                .orElse(0L);
    }

    @Override
    public Long getVerifyDatasetDepth(String symbol, Indicators indicator) {
        var name = getVerifyDatasetParamName(symbol, indicator);
        return repository.findByName(name)
                .map(ProcessParamsEntity::getNumberValue)
                .map(Double::longValue)
                .orElse(0L);
    }

    @Override
    public Long getStepBackSeconds(String symbol, Indicators indicator) {
        var name = getStepBackSecondsParamName(symbol, indicator);
        return repository.findByName(name)
                .map(ProcessParamsEntity::getNumberValue)
                .map(Double::longValue)
                .orElse(0L);
    }

    private String getStepBackSecondsParamName(String symbol, Indicators indicator) {
        return symbol.toUpperCase()
                .concat("_")
                .concat(indicator.name().toUpperCase())
                .concat("_STEP_BACK");
    }

    private String getVerifyDatasetParamName(String symbol, Indicators indicator) {
        return symbol.toUpperCase()
                .concat("_")
                .concat(indicator.name().toUpperCase())
                .concat("_VERIFY_DATASET_DEPTH");
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
