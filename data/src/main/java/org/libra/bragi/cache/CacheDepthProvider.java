package org.libra.bragi.cache;

public interface CacheDepthProvider {

    Boolean isCacheAvailable(String symbol);

    CacheDepths getAllIndicatorsCacheDepths(String symbol);

}
