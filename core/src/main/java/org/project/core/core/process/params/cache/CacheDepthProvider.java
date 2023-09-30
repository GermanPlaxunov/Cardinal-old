package org.project.core.core.process.params.cache;

public interface CacheDepthProvider {

    Boolean isCacheAvailable(String symbol);

    CacheDepths getAllIndicatorsCacheDepths(String symbol);

}
