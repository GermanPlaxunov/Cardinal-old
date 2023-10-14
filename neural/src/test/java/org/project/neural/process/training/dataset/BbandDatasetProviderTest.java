package org.project.neural.process.training.dataset;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.project.data.entities.indicators.AbsolutePriceOscillatorEntity;
import org.project.data.entities.indicators.BollingerBandsEntity;
import org.project.data.services.interfaces.ProcessParamsService;
import org.project.data.services.interfaces.indicators.AbsolutePriceOscillatorService;
import org.project.data.services.interfaces.indicators.BollingerBandsService;
import org.project.neural.TestDataProvider;
import org.project.neural.process.training.dataset.delta.PriceChangeCalculator;
import org.project.neural.process.training.dataset.splitters.CoreStocksSplitter;
import org.project.neural.process.training.dataset.splitters.IndicatorSplitter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BbandDatasetProviderTest {
    private static String symbol = "BTC/USD";
    private static IndicatorSplitter<BollingerBandsEntity> splitter;
    private static BollingerBandsService bollingerBandsService;
    private static PriceChangeCalculator priceChangeCalculator;
    private static ProcessParamsService processParamsService;
    private static CoreStocksSplitter coreStocksSplitter;
    private static BbandDatasetProvider bbandDatasetProvider;

    @BeforeAll
    public static void setUp() {
        splitter = new IndicatorSplitter<>();
        priceChangeCalculator = new PriceChangeCalculator();
        coreStocksSplitter = new CoreStocksSplitter();
        bollingerBandsService = mock(BollingerBandsService.class);
        processParamsService = mock(ProcessParamsService.class);
        bbandDatasetProvider = new BbandDatasetProvider(splitter, bollingerBandsService,
                priceChangeCalculator, processParamsService, coreStocksSplitter);
        when(bollingerBandsService.findCache(eq(symbol), eq(1000L)))
                .thenReturn(TestDataProvider.getBbandList(100));
        when(processParamsService.getTrainInterval(eq(symbol), any()))
                .thenReturn(300L);
        when(processParamsService.getTrainCacheDepth(eq(symbol), any()))
                .thenReturn(1000L);
    }

    @Test
    void getData() {
        var stocks = TestDataProvider.getTestStocks(100);
        var dataset = bbandDatasetProvider.getData(symbol, stocks);
        assertEquals(19, dataset.size());
        for (var item : dataset) {
            assertEquals(-5.0, item.get(0));
        }
    }
}