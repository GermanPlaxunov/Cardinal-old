package org.project.core.core.process.indicators.cache;

public interface CacheDepthProvider {

    Long getCacheDepth(String symbol);

    Boolean isCacheAvailable(String symbol);

}
