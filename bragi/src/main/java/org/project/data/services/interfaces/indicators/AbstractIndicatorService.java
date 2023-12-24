package org.project.data.services.interfaces.indicators;

import java.util.List;

public interface AbstractIndicatorService<T> {

    List<T> findAllBySymbol(String symbol);

    void save(T entity);

    T findLast(String symbol);

    List<T> findCache(String symbol, Long cacheDepthSeconds);
}
