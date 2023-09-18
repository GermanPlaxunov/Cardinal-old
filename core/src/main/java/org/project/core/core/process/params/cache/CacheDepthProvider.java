package org.project.core.core.process.params.cache;

import org.project.core.core.process.params.ActionType;

public interface CacheDepthProvider {

    Long getCacheDepth(String symbol, ActionType actionType);

    Boolean isCacheAvailable(String symbol, ActionType actionType);

}
