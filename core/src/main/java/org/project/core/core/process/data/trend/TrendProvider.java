package org.project.core.core.process.data.trend;

import org.project.model.trend.TrendData;

public interface TrendProvider {

    /**
     * Return data about current trend.
     *
     * @param symbol - stock name.
     * @return trend data.
     */
    TrendData getTrend(String symbol);

}
