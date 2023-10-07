package org.project.neural.process.training.dataset.splitters;

import org.project.data.entities.CoreStockEntity;

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
        var filteredList = new ArrayList<CoreStockEntity>();
        filteredList.add(stocks.get(stocks.size() - 1));
        var lastDate = stocks.get(stocks.size() - 1)
                .getDate()
                .minusSeconds(intervalSeconds);
        for (var i = stocks.size() - 1; i > 0; i--) {
            if (stocks.get(i).getDate().equals(lastDate)) {
                filteredList.add(stocks.get(i));
                lastDate = lastDate.minusSeconds(intervalSeconds);
            }
        }
        return filteredList;
    }

}
