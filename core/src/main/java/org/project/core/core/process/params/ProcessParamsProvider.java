package org.project.core.core.process.params;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.ProcessParamsEntity;
import org.project.data.services.interfaces.ProcessParamsService;

import java.util.Optional;

@RequiredArgsConstructor
public class ProcessParamsProvider {

    private final ProcessParamsService processParamsService;

    public Long getCacheDepth(String symbol, ActionType actionType) {
        var paramName = getCacheDepthParamName(symbol, actionType);
        return Optional.ofNullable(processParamsService.findByName(paramName))
                .map(ProcessParamsEntity::getNumberValue)
                .map(Double::longValue)
                .orElse(null);
    }

    private String getCacheDepthParamName(String symbol, ActionType actionType) {
        return symbol.concat(actionType.name())
                .concat("_CACHE_DEPTH");
    }

}
