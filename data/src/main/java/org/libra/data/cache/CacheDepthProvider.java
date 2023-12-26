package org.libra.data.cache;

public interface CacheDepthProvider {

    Boolean isCacheAvailable(String symbol);

    CacheDepths getAllIndicatorsCacheDepths(String symbol);

}
