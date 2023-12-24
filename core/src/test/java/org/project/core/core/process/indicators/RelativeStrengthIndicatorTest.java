package org.project.core.core.process.indicators;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.project.core.mapper.StockMapper;
import org.project.core.mapper.StockMapperImpl;

import static org.junit.jupiter.api.Assertions.*;

class RelativeStrengthIndicatorTest extends AbstractIndicatorTest {

    private static RelativeStrengthIndicator relativeStrengthIndicator;
    private static StockMapper stockMapper;

    @BeforeAll
    static void setUp() {
        relativeStrengthIndicator = new RelativeStrengthIndicator();
        stockMapper = new StockMapperImpl();
    }

    @Test
    void calculateRsi() {
        var stockEntities = getStocks(10);
        var stocks = stockMapper.mapAllToCore(stockEntities);
        var rsi = relativeStrengthIndicator.calculateRsi(stocks, 7200L);
        assertEquals(44.44444444444444, rsi.getRsi());
    }
}