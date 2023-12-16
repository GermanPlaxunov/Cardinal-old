package org.project.core.core.process.indicators;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.project.core.mapper.StockMapper;
import org.project.core.mapper.StockMapperImpl;

import static org.junit.jupiter.api.Assertions.*;

class StandardDerivativesTest extends AbstractIndicatorTest {

    private static StandardDerivatives standardDerivatives;
    private static StockMapper stockMapper;

    @BeforeAll
    static void setUp() {
        var simpleMovingAverage = new SimpleMovingAverage();
        standardDerivatives = new StandardDerivatives(simpleMovingAverage);
        stockMapper = new StockMapperImpl();
    }

    @Test
    void calculateStd() {
        var stockEntities = getStocks(5);
        var stocks = stockMapper.mapAllToCore(stockEntities);
        var std = standardDerivatives.calculateStd(stocks, 5L);
        assertEquals(2.4166091947189146, std);
    }
}