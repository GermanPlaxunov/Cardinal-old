package org.project.core.core.process.decision.indicators;

import org.project.core.core.process.decision.indicators.apo.ApoDecisionProcessor;
import org.project.core.core.process.decision.indicators.bband.BbandDecisionProcessor;
import org.project.core.core.process.decision.indicators.ema.EmaDecisionProcessor;
import org.project.core.core.process.decision.indicators.rsi.RsiDecisionProcessor;
import org.project.core.core.process.decision.indicators.sma.SmaDecisionProcessor;
import org.project.core.core.process.decision.indicators.std.StdDerivsDecisionProcessor;
import org.project.model.IndicatorType;

import java.util.HashMap;
import java.util.Map;

import static org.project.model.IndicatorType.*;

public class DecisionProcessorsStore {

    private final Map<IndicatorType, IndicatorDecisionProcessor> processors;

    public DecisionProcessorsStore(ApoDecisionProcessor apoDecisionProcessor,
                                   BbandDecisionProcessor bbandDecisionProcessor,
                                   EmaDecisionProcessor emaDecisionProcessor,
                                   RsiDecisionProcessor rsiDecisionProcessor,
                                   SmaDecisionProcessor smaDecisionProcessor,
                                   StdDerivsDecisionProcessor stdDerivsDecisionProcessor) {
        processors = new HashMap<>();
        processors.put(INDICATOR_TYPE_APO, apoDecisionProcessor);
        processors.put(INDICATOR_TYPE_BBAND, bbandDecisionProcessor);
        processors.put(INDICATOR_TYPE_EMA, emaDecisionProcessor);
        processors.put(INDICATOR_TYPE_RSI, rsiDecisionProcessor);
        processors.put(INDICATOR_TYPE_SMA, smaDecisionProcessor);
        processors.put(INDICATOR_TYPE_STD, stdDerivsDecisionProcessor);
    }

    public IndicatorDecisionProcessor get(IndicatorType type) {
        return processors.get(type);
    }

}
