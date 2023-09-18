package org.project.core.core.process.params.cache;

import lombok.RequiredArgsConstructor;
import org.project.core.core.process.params.ActionType;
import org.project.data.entities.ProcessParamsEntity;
import org.project.data.services.interfaces.CoreStockService;
import org.project.data.services.interfaces.ProcessParamsService;

import java.util.Optional;

@RequiredArgsConstructor
public class CacheDepthProviderImpl implements CacheDepthProvider {

    private final ProcessParamsService processParamsService;
    private final CoreStockService coreStockService;

    @Override
    public Long getCacheDepth(String symbol, ActionType actionType) {
        var name = getParamName(symbol, actionType);
        return Optional.ofNullable(processParamsService.findByName(name))
                .map(ProcessParamsEntity::getNumberValue)
                .map(Double::longValue)
                .orElse(null);
    }

    @Override
    public Boolean isCacheAvailable(String symbol, ActionType actionType) {
        var depth = getCacheDepth(symbol, actionType);
        return coreStockService.findCache(symbol, depth)
                .stream()
                .count() > 0;
    }

    private String getParamName(String symbol, ActionType actionType) {
        return symbol.concat(actionType.name())
                .concat("_CACHE_DEPTH");
    }

}
