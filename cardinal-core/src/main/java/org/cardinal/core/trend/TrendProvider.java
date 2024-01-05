package org.cardinal.core.trend;

import org.cardinal.model.CoreStock;
import org.cardinal.model.trend.TrendData;

import java.util.List;

public interface TrendProvider {

    /**
     * Return data about current trend.
     *
     * @param symbol - stock name.
     * @return trend data.
     */
    TrendData getTrend(String symbol, List<CoreStock> stocks);

}
