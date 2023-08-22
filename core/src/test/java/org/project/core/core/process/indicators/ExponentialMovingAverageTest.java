package org.project.core.core.process.indicators;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.project.data.services.interfaces.CoreStockService;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExponentialMovingAverageTest extends AbstractIndicatorTest {

    private static ExponentialMovingAverage exponentialMovingAverage;

    @BeforeAll
    static void setUp() {
        exponentialMovingAverage = new ExponentialMovingAverage();
    }

    @Test
    void calculateEma() {
        var stocks = getStocks(5);
        var ema = exponentialMovingAverage.calculateEma(stocks);
        assertEquals(100.86419753086419, ema);
    }
}