package org.project.core.core.process.indicators;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.project.core.mapper.StockMapper;
import org.project.core.mapper.StockMapperImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BollingerBandsTest extends AbstractIndicatorTest {

    private static BollingerBands bollingerBands;
    private static StockMapper stockMapper;

    @BeforeAll
    static void setUp() {
        var exponentialMovingAverage = new ExponentialMovingAverage();
        bollingerBands = new BollingerBands(exponentialMovingAverage);
        stockMapper = new StockMapperImpl();
    }

    @Test
    void calculateBband() {
        var stockEntities = getStocks(10);
        var stocks = stockMapper.mapAllToCore(stockEntities);
        var bband = bollingerBands.calculateBband(stocks, 10L);
        assertEquals(88.41746050246414, bband.getLower());
        assertEquals(99.04760631519879, bband.getMiddle());
        assertEquals(109.67775212793345, bband.getUpper());
    }
}