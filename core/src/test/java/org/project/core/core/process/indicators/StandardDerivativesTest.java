package org.project.core.core.process.indicators;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StandardDerivativesTest extends AbstractIndicatorTest {

    private static StandardDerivatives standardDerivatives;

    @BeforeAll
    static void setUp() {
        var simpleMovingAverage = new SimpleMovingAverage();
        standardDerivatives = new StandardDerivatives(simpleMovingAverage);
    }

    @Test
    void calculateStd() {
        var stocks = getStocks(5);
        var std = standardDerivatives.calculateStd(stocks);
        assertEquals(2.4166091947189146, std);
    }
}