package org.project.core.core.process.data.trend;

import lombok.RequiredArgsConstructor;
import org.project.core.core.process.Utils;
import org.libra.data.services.interfaces.ProcessParamsService;
import org.project.model.CoreStock;
import org.project.model.trend.TrendData;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class AveragePriceTrendProvider implements TrendProvider {

    private final ProcessParamsService processParamsService;
    private final StocksDivider stocksDivider;

    /**
     * Finds current trend.
     * Dummy logic.
     *
     * @param symbol - stock name.
     * @return trend data.
     */
    @Override
    public TrendData getTrend(String symbol, List<CoreStock> stocks) {
        var cacheDepth = processParamsService.getShortTrendCacheDepth(symbol);

        var periods = stocksDivider.divideStocksOnPeriods(symbol, stocks);
        var averages = new ArrayList<Double>();
        for (var i = 0; i < periods.size(); i++) {
            averages.add(getAverageOfPeriod(periods.get(i)));
        }
        var data = new TrendData();
        data.setTrendStartDate(stocks.get(0).getDate());
        data.setTrendStartPrice(stocks.get(0).getClose());
        data.setTrendEndDate(stocks.get(stocks.size() - 1).getDate());
        data.setTrendEndPrice(stocks.get(stocks.size() - 1).getClose());
        data.setUpTrend(isUpTrend(averages));
        data.setPeriodInSeconds(cacheDepth);
        return data;
    }

    /**
     * Returns average value of the period.
     *
     * @param stocks - the list of stocks in the period.
     * @return average value of the period.
     */
    private Double getAverageOfPeriod(List<CoreStock> stocks) {
        var min = Utils.getMinClose(stocks);
        var max = Utils.getMaxClose(stocks);
        var offset = (max - min) / 2;
        return min + offset;
    }

    private boolean isUpTrend(List<Double> averages) {
        return averages.get(0) < averages.get(averages.size() - 1);
    }

}
