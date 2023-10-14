package org.project.neural.process.training.dataset.splitters;

import org.project.data.entities.CoreStockEntity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class CoreStocksSplitter {

    /**
     * Filters out all stocks lying between edge stocks
     * of interval starting from the latest date. Stocks
     * should be sorted in asc mode by date.
     *
     * @param stocks          - the list of stocks.
     * @param intervalSeconds - interval between each sequent stocks.
     * @return filtered list of stocks.
     */
    public List<CoreStockEntity> split(List<CoreStockEntity> stocks, Long intervalSeconds) {
        if (ifSplittingCanBeApplied(stocks, intervalSeconds)) {
            var filteredList = new ArrayList<CoreStockEntity>();
            filteredList.add(stocks.get(stocks.size() - 1));
            var stepDate = stocks.get(stocks.size() - 1)
                    .getDate()
                    .minusSeconds(intervalSeconds);
            for (var i = stocks.size() - 1; i > 0; i--) {
                if (checkDate(stocks, i, stepDate)) {
                    filteredList.add(stocks.get(i));
                    stepDate = stepDate.minusSeconds(intervalSeconds);
                }
            }
            return filteredList;
        } else {
            return stocks;
        }
    }

    /**
     * Checks if current stock is fit as edge of interval.
     *
     * @param stocks  - list of stocks.
     * @param current - checking stock.
     * @param stepDate - edge date of the step.
     * @return true if current should be added to the list.
     */
    private boolean checkDate(List<CoreStockEntity> stocks, Integer current, LocalDateTime stepDate) {
        var currentDate = stocks.get(current).getDate();
        var prevDate = stocks.get(current - 1).getDate();
        if (currentDate.equals(stepDate)) {
            return true;
        } else {
            if (prevDate.equals(stepDate)) {
                return false;
            }
            if (currentDate.isAfter(stepDate) && prevDate.isBefore(stepDate)) {
                return true;
            }
            if (currentDate.isAfter(stepDate) && prevDate.isAfter(stepDate)) {
                return false;
            }
        }
        return false;
    }

    /**
     * Checks if the distance between two stocks not
     * larger than the interval to split.
     *
     * @param stocks       - original list of stocks.
     * @param stepInterval - split interval.
     * @return true if splitting can be applied.
     */
    private boolean ifSplittingCanBeApplied(List<CoreStockEntity> stocks,
                                            long stepInterval) {
        var date_1 = stocks.get(stocks.size() - 1).getDate();
        var date_2 = stocks.get(stocks.size() - 2).getDate();
        var duration = ChronoUnit.SECONDS.between(date_2, date_1);
        return duration < stepInterval;
    }

}
