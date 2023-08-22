package org.project.core.core.process.indicators;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BollingerBandsTest extends AbstractIndicatorTest {

    private static BollingerBands bollingerBands;

    @BeforeAll
    static void setUp() {
        var exponentialMovingAverage = new ExponentialMovingAverage();
        bollingerBands = new BollingerBands(exponentialMovingAverage);
    }

    @Test
    void calculateBband() {
        var stocks = getStocks(10);
        var bband = bollingerBands.calculateBband(stocks);
        assertEquals(88.41746050246414, bband.getLower());
        assertEquals(99.04760631519879, bband.getMiddle());
        assertEquals(109.67775212793345, bband.getUpper());
    }
}