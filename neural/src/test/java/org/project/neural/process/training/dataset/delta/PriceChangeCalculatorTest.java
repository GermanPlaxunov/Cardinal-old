package org.project.neural.process.training.dataset.delta;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.project.neural.TestDataProvider;

import static org.junit.jupiter.api.Assertions.*;

class PriceChangeCalculatorTest {

    private static PriceChangeCalculator priceChangeCalculator;

    @BeforeAll
    public static void setUp() {
        priceChangeCalculator = new PriceChangeCalculator();
    }

    @Test
    void getPriceChanges() {
        var stocks = TestDataProvider.getTestStocks(5);
        var changes = priceChangeCalculator.getPriceChanges(stocks);
        for(var change : changes) {
            assertEquals(1.0, change, "Assert price change");
        }
        assertEquals(changes.size(), stocks.size() - 1, "Assert lists size");
    }
}