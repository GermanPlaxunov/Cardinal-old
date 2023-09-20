package org.project.core.core.process.params.cache;

import org.project.core.core.process.indicators.Indicators;

public interface CacheDepthProvider {

    Long getCacheDepth(String symbol, Indicators indicator);

    Boolean isCacheAvailable(String symbol);

    CacheDepths getAllIndicatorsCacheDepths(String symbol);

}
