package org.project.core.core.process.decision.indicators;

import org.project.core.core.process.decision.indicators.apo.ApoDecisionProcessor;
import org.project.core.core.process.decision.indicators.bband.BbandDecisionProcessor;
import org.project.core.core.process.decision.indicators.ema.EmaDecisionProcessor;
import org.project.core.core.process.decision.indicators.rsi.RsiDecisionProcessor;
import org.project.core.core.process.decision.indicators.sma.SmaDecisionProcessor;
import org.project.core.core.process.decision.indicators.std.StdDerivsDecisionProcessor;
import org.project.model.Indicators;

import java.util.HashMap;
import java.util.Map;

public class DecisionProcessorsStore {

    private final Map<Indicators, IndicatorDecisionProcessor> processors;

    public DecisionProcessorsStore(ApoDecisionProcessor apoDecisionProcessor,
                                   BbandDecisionProcessor bbandDecisionProcessor,
                                   EmaDecisionProcessor emaDecisionProcessor,
                                   RsiDecisionProcessor rsiDecisionProcessor,
                                   SmaDecisionProcessor smaDecisionProcessor,
                                   StdDerivsDecisionProcessor stdDerivsDecisionProcessor) {
        processors = new HashMap<>();
        processors.put(Indicators.APO, apoDecisionProcessor);
        processors.put(Indicators.BBAND, bbandDecisionProcessor);
        processors.put(Indicators.EMA, emaDecisionProcessor);
        processors.put(Indicators.RSI, rsiDecisionProcessor);
        processors.put(Indicators.SMA, smaDecisionProcessor);
        processors.put(Indicators.STD, stdDerivsDecisionProcessor);
    }

    public IndicatorDecisionProcessor get(Indicators indicator) {
        return processors.get(indicator);
    }

}
