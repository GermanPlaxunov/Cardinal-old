package org.project.neural.process.training.dataset;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.project.data.entities.indicators.ExponentialMovingAverageEntity;
import org.project.data.services.interfaces.ProcessParamsService;
import org.project.data.services.interfaces.indicators.ExponentialMovingAverageService;
import org.project.neural.TestDataProvider;
import org.project.neural.process.training.dataset.delta.PriceChangeCalculator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmaDatasetProviderTest {

    private static String symbol = "BTC/USD";
    private static IndicatorSplitter<ExponentialMovingAverageEntity> splitter;
    private static ExponentialMovingAverageService exponentialMovingAverageService;
    private static PriceChangeCalculator priceChangeCalculator;
    private static ProcessParamsService processParamsService;
    private static CoreStocksSplitter coreStocksSplitter;
    private static EmaDatasetProvider emaDatasetProvider;

    @BeforeAll
    public static void setUp() {
        splitter = new IndicatorSplitter<>();
        priceChangeCalculator = new PriceChangeCalculator();
        coreStocksSplitter = new CoreStocksSplitter();
        exponentialMovingAverageService = mock(ExponentialMovingAverageService.class);
        processParamsService = mock(ProcessParamsService.class);
        emaDatasetProvider = new EmaDatasetProvider(splitter, exponentialMovingAverageService,
                priceChangeCalculator, processParamsService, coreStocksSplitter);
        when(exponentialMovingAverageService.findCache(eq(symbol), eq(1000L)))
                .thenReturn(TestDataProvider.getEmaList(100));
        when(processParamsService.getTrainInterval(eq(symbol), any()))
                .thenReturn(300L);
        when(processParamsService.getTrainCacheDepth(eq(symbol), any()))
                .thenReturn(1000L);
    }

    @Test
    void getData() {
        var stocks = TestDataProvider.getTestStocks(100);
        var dataset = emaDatasetProvider.getData(symbol, stocks);
        assertEquals(19, dataset.size());
        for (var item : dataset) {
            assertEquals(-5.0, item.get(0));
        }
    }
}