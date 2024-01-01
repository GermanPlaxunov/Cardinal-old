package org.libra.data.services.classes;

import lombok.RequiredArgsConstructor;
import org.libra.data.entities.ProcessParamsEntity;
import org.libra.data.repositories.ProcessParamsRepository;
import org.libra.data.services.interfaces.ProcessParamsService;
import org.project.model.Indicators;

import java.util.ArrayList;
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
                .orElse(null);
    }

    @Override
    public Long getTrainInterval(String symbol, Indicators indicator) {
        var paramName = getTrainIntervalName(symbol, indicator);
        return repository.findByName(paramName)
                .map(ProcessParamsEntity::getNumberValue)
                .map(Double::longValue)
                .orElse(null);
    }

    @Override
    public Long getTrainCacheDepth(String symbol, Indicators indicator) {
        var name = getTrainCacheDepthName(symbol, indicator);
        return repository.findByName(name)
                .map(ProcessParamsEntity::getNumberValue)
                .map(Double::longValue)
                .orElse(null);
    }

    @Override
    public Long getVerifyDatasetDepth(String symbol, Indicators indicator) {
        var name = getVerifyDatasetParamName(symbol, indicator);
        return repository.findByName(name)
                .map(ProcessParamsEntity::getNumberValue)
                .map(Double::longValue)
                .orElse(null);
    }

    @Override
    public Long getStepBackSeconds(String symbol, Indicators indicator) {
        var name = getStepBackSecondsParamName(symbol, indicator);
        return repository.findByName(name)
                .map(ProcessParamsEntity::getNumberValue)
                .map(Double::longValue)
                .orElse(null);
    }

    @Override
    public Long getShortTrendCacheDepth(String symbol) {
        var name = getShortTrendCacheDepthName(symbol);
        return repository.findByName(name)
                .map(ProcessParamsEntity::getNumberValue)
                .map(Double::longValue)
                .orElse(null);
    }

    @Override
    public Long getNumberOfPeriodsToFindTrend(String symbol) {
        var name = getNumberOfPeriodsToFindTrendName(symbol);
        return repository.findByName(name)
                .map(ProcessParamsEntity::getNumberValue)
                .map(Double::longValue)
                .orElse(null);
    }

    @Override
    public Long getMaximumCacheDepth(String symbol) {
        var names = new ArrayList<String>();
        names.add(getShortTrendCacheDepthName(symbol));
        return repository.findAllByNameIn(names)
                .stream()
                .map(ProcessParamsEntity::getNumberValue)
                .map(Double::longValue)
                .max(Long::compareTo)
                .orElse(null);
    }

    @Override
    public Double getBuyCommissionPercentage(String symbol) {
        var name = getBuyCommissionPercentageName(symbol);
        return repository.findByName(name)
                .map(ProcessParamsEntity::getNumberValue)
                .orElse(null);
    }

    @Override
    public Double getSellCommissionPercentage(String symbol) {
        var name = getSellCommissionPercentageName(symbol);
        return repository.findByName(name)
                .map(ProcessParamsEntity::getNumberValue)
                .orElse(null);
    }

    @Override
    public Double getMaxBalanceShareForTrade() {
        return repository.findByName("MAX_BALANCE_SHARE_FOR_TRADE")
                .map(ProcessParamsEntity::getNumberValue)
                .orElse(null);
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

    private String getShortTrendCacheDepthName(String symbol) {
        return symbol.toUpperCase()
                .concat("_SHORT_TREND_CACHE_DEPTH");
    }

    private String getNumberOfPeriodsToFindTrendName(String symbol) {
        return symbol.toUpperCase()
                .concat("_NUMBER_OF_PERIODS_TO_FIND_TREND");
    }

    private String getBuyCommissionPercentageName(String symbol) {
        return symbol.toUpperCase()
                .concat("_BUY_COMMISSION_PERCENTAGE");
    }

    private String getSellCommissionPercentageName(String symbol) {
        return symbol.toUpperCase()
                .concat("_SELL_COMMISSION_PERCENTAGE");
    }
}
