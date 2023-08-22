package org.project.core.core.process.indicators;

import org.project.data.entities.CoreStockEntity;

import java.util.ArrayList;
import java.util.List;

public class AbstractIndicatorTest {

    protected List<CoreStockEntity> getStocks(int size) {
        var stocks = new ArrayList<CoreStockEntity>();
        var init = 100.0;
        var neg = false;
        for (var i = 0; i < size; i++) {
            var close = init + ((neg ? -1 : 1) * i);
            stocks.add(new CoreStockEntity()
                    .setSymbol("BTC/USD")
                    .setClose(close));
            neg = !neg;
        }
        return stocks;
    }

}
