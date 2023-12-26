package org.project.core.core.process.data.trend;

import lombok.RequiredArgsConstructor;
import org.libra.data.services.interfaces.ProcessParamsService;
import org.project.model.CoreStock;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class StocksDivider {

    private final ProcessParamsService processParamsService;

    /**
     * Divide all stocks on periods of certain size.
     *
     * @param symbol - stock name.
     * @param stocks - list of the stocks.
     * @return the list of periods.
     */
    public List<List<CoreStock>> divideStocksOnPeriods(String symbol, List<CoreStock> stocks) {
        var numberOfPeriods = processParamsService.getNumberOfPeriodsToFindTrend(symbol);
        var dateFrom = stocks.get(0).getDate();
        var dateTo = stocks.get(stocks.size() - 1).getDate();
        var deltaSeconds = ChronoUnit.SECONDS.between(dateFrom, dateTo);
        var periodLen = deltaSeconds / numberOfPeriods;
        var periods = new ArrayList<List<CoreStock>>();
        var dateToPeriod = dateFrom.plusSeconds(periodLen);
        for (var i = 0; i < numberOfPeriods; i++) {
            periods.add(getNextPeriod(stocks, dateFrom, dateToPeriod));
            dateFrom.plusSeconds(periodLen);
            dateTo.plusSeconds(periodLen);
        }
        return periods;
    }

    /**
     * Trims all stocks where date is before
     * fromDate and after toDate.
     *
     * @param stocks - list of stocks.
     * @param from   - start date of period.
     * @param to     - end date of period.
     * @return the list of stocks in period.
     */
    private List<CoreStock> getNextPeriod(List<CoreStock> stocks, LocalDateTime from, LocalDateTime to) {
        return stocks.stream()
                .filter(stock -> stock.getDate().isAfter(from))
                .filter(stock -> stock.getDate().isBefore(to))
                .toList();
    }

}
