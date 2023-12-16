package org.project.core.core.process.indicators;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.project.core.mapper.StockMapper;
import org.project.core.mapper.StockMapperImpl;

import static org.junit.jupiter.api.Assertions.*;

class SimpleMovingAverageTest extends AbstractIndicatorTest {

    private static SimpleMovingAverage simpleMovingAverage;
    private static StockMapper stockMapper;

    @BeforeAll
    static void setUp() {
        simpleMovingAverage = new SimpleMovingAverage();
        stockMapper = new StockMapperImpl();
    }

    @Test
    void calculateSma() {
        var stockEntities = getStocks(5);
        var stocks = stockMapper.mapAllToCore(stockEntities);
        var sma = simpleMovingAverage.calculateSma(stocks, 5L);
        assertEquals(100.4, sma);
    }
}