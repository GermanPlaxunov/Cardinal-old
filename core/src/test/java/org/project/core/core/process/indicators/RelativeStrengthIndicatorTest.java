package org.project.core.core.process.indicators;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RelativeStrengthIndicatorTest extends AbstractIndicatorTest {

    private static RelativeStrengthIndicator relativeStrengthIndicator;

    @BeforeAll
    static void setUp() {
        relativeStrengthIndicator = new RelativeStrengthIndicator();
    }

    @Test
    void calculateRsi() {
        var stocks = getStocks(10);
        var rsi = relativeStrengthIndicator.calculateRsi(stocks);
        assertEquals(44.44444444444444, rsi.getRsi());
    }
}