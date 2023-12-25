package org.project.core.core.process.indicators;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.project.core.mapper.StockMapper;
import org.project.core.mapper.StockMapperImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExponentialMovingAverageTest extends AbstractIndicatorTest {

    private static ExponentialMovingAverage exponentialMovingAverage;
    private static StockMapper stockMapper;

    @BeforeAll
    static void setUp() {
        exponentialMovingAverage = new ExponentialMovingAverage();
        stockMapper = new StockMapperImpl();
    }

    @Test
    void calculateEma() {
        var stockEntities = getStocks(5);
        var stocks = stockMapper.mapAllToCore(stockEntities);
        var ema = exponentialMovingAverage.calculateEma(stocks, 7200L);
        assertEquals(100.86419753086419, ema);
    }
}