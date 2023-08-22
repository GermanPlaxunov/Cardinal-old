package org.project.core.core.process.indicators.cache;

import lombok.RequiredArgsConstructor;
import org.project.data.services.interfaces.CoreStockService;

@RequiredArgsConstructor
public class BtcCacheDepthProvider implements CacheDepthProvider {

    private final CoreStockService coreStockService;

    public Long getCacheDepth(String symbol) {
        return 600L;
    }

    public Boolean isCacheAvailable(String symbol) {
        return coreStockService.count(symbol) > 600L;
    }

}
