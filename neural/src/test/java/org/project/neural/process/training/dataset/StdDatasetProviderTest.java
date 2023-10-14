package org.project.neural.process.training.dataset;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.project.data.entities.indicators.SimpleMovingAverageEntity;
import org.project.data.entities.indicators.StandardDerivativesEntity;
import org.project.data.services.interfaces.ProcessParamsService;
import org.project.data.services.interfaces.indicators.SimpleMovingAverageService;
import org.project.data.services.interfaces.indicators.StandardDerivativesService;
import org.project.neural.TestDataProvider;
import org.project.neural.process.training.dataset.delta.PriceChangeCalculator;
import org.project.neural.process.training.dataset.splitters.CoreStocksSplitter;
import org.project.neural.process.training.dataset.splitters.IndicatorSplitter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StdDatasetProviderTest {
    private static String symbol = "BTC/USD";
    private static IndicatorSplitter<StandardDerivativesEntity> splitter;
    private static StandardDerivativesService standardDerivativesService;
    private static PriceChangeCalculator priceChangeCalculator;
    private static ProcessParamsService processParamsService;
    private static CoreStocksSplitter coreStocksSplitter;
    private static StdDatasetProvider stdDatasetProvider;

    @BeforeAll
    public static void setUp() {
        splitter = new IndicatorSplitter<>();
        priceChangeCalculator = new PriceChangeCalculator();
        coreStocksSplitter = new CoreStocksSplitter();
        standardDerivativesService = mock(StandardDerivativesService.class);
        processParamsService = mock(ProcessParamsService.class);
        stdDatasetProvider = new StdDatasetProvider(splitter, standardDerivativesService,
                priceChangeCalculator, processParamsService, coreStocksSplitter);
        when(standardDerivativesService.findCache(eq(symbol), eq(1000L)))
                .thenReturn(TestDataProvider.getStdList(100));
        when(processParamsService.getTrainInterval(eq(symbol), any()))
                .thenReturn(300L);
        when(processParamsService.getTrainCacheDepth(eq(symbol), any()))
                .thenReturn(1000L);
    }

    @Test
    void getData() {
        var stocks = TestDataProvider.getTestStocks(100);
        var dataset = stdDatasetProvider.getData(symbol, stocks);
        assertEquals(18, dataset.size());
        for (var item : dataset) {
            assertEquals(-5.0, item.get(0));
        }
    }
}