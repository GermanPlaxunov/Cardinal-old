package org.project.data.cache;

public interface CacheDepthProvider {

    Boolean isCacheAvailable(String symbol);

    CacheDepths getAllIndicatorsCacheDepths(String symbol);

}
