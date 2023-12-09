package org.project.core.core.process.indicators;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.project.core.mapper.StockMapper;
import org.project.core.mapper.StockMapperImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AbsolutePriceOscillatorTest extends AbstractIndicatorTest {

    private static AbsolutePriceOscillator absolutePriceOscillator;
    private static StockMapper stockMapper;

    @BeforeAll
    static void setUp() {
        var exponentialMovingAverage = new ExponentialMovingAverage();
        absolutePriceOscillator = new AbsolutePriceOscillator(exponentialMovingAverage);
        stockMapper = new StockMapperImpl();
    }

    @Test
    void calculateApo() {
        var stockEntitiesSlow = getStocks(5);
        var stocksSlow = stockMapper.mapAllToCore(stockEntitiesSlow);
        var apo = absolutePriceOscillator.calculateApo(stocksSlow, 5L);
        assertEquals(0.8858024691358111, apo);
    }
}