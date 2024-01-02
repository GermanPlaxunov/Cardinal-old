package org.cardinal.neural.process.training.dataset.delta;

import org.cardinal.data.entities.CoreStockEntity;

import java.util.ArrayList;
import java.util.List;

public class PriceChangeCalculator {

    /**
     * Returns the deltas of close prise
     * between each pair of stocks.
     * Stocks should be ordered by date in asc mode.
     *
     * @param stocks - list of stocks.
     * @return list of price changes.
     */
    public List<Double> getPriceChanges(List<CoreStockEntity> stocks) {
        var deltas = new ArrayList<Double>();
        for (var i = 0; i < stocks.size() - 1; i++) {
            deltas.add(stocks.get(i + 1).getClose() - stocks.get(i).getClose());
        }
        return deltas;
    }

}
