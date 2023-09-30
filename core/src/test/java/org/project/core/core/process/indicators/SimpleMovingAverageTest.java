package org.project.core.core.process.indicators;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleMovingAverageTest extends AbstractIndicatorTest {

    private static SimpleMovingAverage simpleMovingAverage;

    @BeforeAll
    static void setUp() {
        simpleMovingAverage = new SimpleMovingAverage();
    }

    @Test
    void calculateSma() {
        var stocks = getStocks(5);
        var sma = simpleMovingAverage.calculateSma(stocks, 5L);
        assertEquals(100.4, sma);
    }
}