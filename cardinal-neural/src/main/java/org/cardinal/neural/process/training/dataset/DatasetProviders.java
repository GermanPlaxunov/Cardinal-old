package org.cardinal.neural.process.training.dataset;

import org.project.model.Indicators;

import java.util.HashMap;
import java.util.Map;

public class DatasetProviders {

    private final Map<Indicators, DatasetProvider> providers;

    public DatasetProviders(ApoDatasetProvider apoDatasetProvider,
                            BbandDatasetProvider bbandDatasetProvider,
                            EmaDatasetProvider emaDatasetProvider,
                            RsiDatasetProvider rsiDatasetProvider,
                            SmaDatasetProvider smaDatasetProvider,
                            StdDatasetProvider stdDatasetProvider) {
        this.providers = new HashMap<>();
        providers.put(Indicators.APO, apoDatasetProvider);
        providers.put(Indicators.BBAND, bbandDatasetProvider);
        providers.put(Indicators.EMA, emaDatasetProvider);
        providers.put(Indicators.RSI, rsiDatasetProvider);
        providers.put(Indicators.SMA, smaDatasetProvider);
        providers.put(Indicators.STD, stdDatasetProvider);
    }

    public DatasetProvider get(Indicators indicator) {
        return providers.get(indicator);
    }

}
