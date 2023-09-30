package org.project.core.core.process.indicators;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbsolutePriceOscillatorTest extends AbstractIndicatorTest {

    private static AbsolutePriceOscillator absolutePriceOscillator;

    @BeforeAll
    static void setUp() {
        var exponentialMovingAverage = new ExponentialMovingAverage();
        absolutePriceOscillator = new AbsolutePriceOscillator(exponentialMovingAverage);
    }

    @Test
    void calculateApo() {
        var stocksSlow = getStocks(5);
        var apo = absolutePriceOscillator.calculateApo(stocksSlow, 5L);
        assertEquals(0.8858024691358111, apo);
    }
}