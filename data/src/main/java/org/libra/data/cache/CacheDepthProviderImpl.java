package org.libra.data.cache;

import lombok.RequiredArgsConstructor;
import org.libra.data.services.interfaces.CoreStockService;
import org.libra.data.services.interfaces.ProcessParamsService;
import org.project.model.Indicators;

import java.util.Arrays;

@RequiredArgsConstructor
public class CacheDepthProviderImpl implements CacheDepthProvider {

    private final ProcessParamsService processParamsService;
    private final CacheDepthMapper cacheDepthMapper;
    private final CoreStockService coreStockService;

    @Override
    public Boolean isCacheAvailable(String symbol) {
        return coreStockService.findCache(symbol, 5000L)
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
