package org.project.neural.process.training.dataset;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.project.data.entities.indicators.AbsolutePriceOscillatorEntity;
import org.project.data.services.interfaces.ProcessParamsService;
import org.project.data.services.interfaces.indicators.AbsolutePriceOscillatorService;
import org.project.neural.TestDataProvider;
import org.project.neural.process.training.dataset.delta.PriceChangeCalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ApoDatasetProviderTest {
    private static String symbol = "BTC/USD";
    private static IndicatorSplitter<AbsolutePriceOscillatorEntity> splitter;
    private static AbsolutePriceOscillatorService absolutePriceOscillatorService;
    private static PriceChangeCalculator priceChangeCalculator;
    private static ProcessParamsService processParamsService;
    private static CoreStocksSplitter coreStocksSplitter;
    private static ApoDatasetProvider apoDatasetProvider;

    @BeforeAll
    public static void setUp() {
        splitter = new IndicatorSplitter<>();
        priceChangeCalculator = new PriceChangeCalculator();
        coreStocksSplitter = new CoreStocksSplitter();
        absolutePriceOscillatorService = mock(AbsolutePriceOscillatorService.class);
        processParamsService = mock(ProcessParamsService.class);
        apoDatasetProvider = new ApoDatasetProvider(splitter, absolutePriceOscillatorService,
                priceChangeCalculator, processParamsService, coreStocksSplitter);
        when(absolutePriceOscillatorService.findCache(eq(symbol), eq(1000L)))
                .thenReturn(TestDataProvider.getApoList(100));
        when(processParamsService.getTrainInterval(eq(symbol), any()))
                .thenReturn(300L);
        when(processParamsService.getTrainCacheDepth(eq(symbol), any()))
                .thenReturn(1000L);
    }

    @Test
    void getData() {
        var stocks = TestDataProvider.getTestStocks(100);
        var dataset = apoDatasetProvider.getData(symbol, stocks);
        assertEquals(19, dataset.size());
        for(var item : dataset) {
            assertEquals(-5.0, item.get(0));
        }
    }
}