package org.libra.decision.processor.indicators;

import lombok.RequiredArgsConstructor;
import org.project.model.Indicators;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class IndicatorProcessorsStore {

    private final Map<Indicators, IndicatorProcessor> processors;

    public IndicatorProcessorsStore(ApoProcessor apoProcessor,
                                    BbandProcessor bbandProcessor,
                                    EmaProcessor emaProcessor,
                                    RsiProcessor rsiProcessor,
                                    SmaProcessor smaProcessor,
                                    StdProcessor stdProcessor) {
        processors = new HashMap<>();
        processors.put(Indicators.APO, apoProcessor);
        processors.put(Indicators.BBAND, bbandProcessor);
        processors.put(Indicators.EMA, emaProcessor);
        processors.put(Indicators.RSI, rsiProcessor);
        processors.put(Indicators.SMA, smaProcessor);
        processors.put(Indicators.STD, stdProcessor);
    }

    public IndicatorProcessor get(Indicators indicator) {
        return processors.get(indicator);
    }

}
