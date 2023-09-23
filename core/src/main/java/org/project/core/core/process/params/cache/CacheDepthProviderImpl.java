package org.project.core.core.process.params.cache;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.ProcessParamsEntity;
import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.ProcessParamsService;
import org.project.model.Indicators;

import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
public class CacheDepthProviderImpl implements CacheDepthProvider {

    private final ProcessParamsService processParamsService;
    private final CacheDepthMapper cacheDepthMapper;
    private final CoreStockService coreStockService;

    @Override
    public Long getCacheDepth(String symbol, Indicators indicator) {
        var name = getParamName(symbol, indicator);
        return Optional.ofNullable(processParamsService.findByName(name))
                .map(ProcessParamsEntity::getNumberValue)
                .map(Double::longValue)
                .orElse(null);
    }

    @Override
    public Boolean isCacheAvailable(String symbol) {
        return coreStockService.findCache(symbol, 3000L)
                .stream()
                .count() > 0;
    }

    @Override
    public CacheDepths getAllIndicatorsCacheDepths(String symbol) {
        var names = Arrays.stream(Indicators.values())
                .map(name -> getParamName(symbol, name))
                .toList();
        return cacheDepthMapper.map(processParamsService
                .findAllByNames(names));
    }

    private String getParamName(String symbol, Indicators indicator) {
        return symbol.concat("_")
                .concat(indicator.name())
                .concat("_CACHE_DEPTH");
    }

}
