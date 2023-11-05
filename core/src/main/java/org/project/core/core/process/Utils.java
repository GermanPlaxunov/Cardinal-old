package org.project.core.core.process;

import org.project.data.entities.CoreStockEntity;

import java.util.List;

public class Utils {

    public static Double getMinClose(List<CoreStockEntity> stocks) {
        return stocks.stream()
                .map(CoreStockEntity::getClose)
                .min(Double::compareTo)
                .orElse(null);
    }

    public static Double getMaxClose(List<CoreStockEntity> stocks) {
        return stocks.stream()
                .map(CoreStockEntity::getClose)
                .max(Double::compareTo)
                .orElse(null);
    }

}
