package org.project.core.core.process.indicators.cache;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BtcCacheDepthProvider implements CacheDepthProvider {

    public Long getCacheDepth(String symbol) {
        return 30L;
    }

}
