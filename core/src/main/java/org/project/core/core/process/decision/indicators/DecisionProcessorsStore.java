package org.project.core.core.process.decision.indicators;

import org.project.core.core.process.decision.indicators.rsi.RsiDecisionProcessor;
import org.project.model.IndicatorType;

import java.util.HashMap;
import java.util.Map;

import static org.project.model.IndicatorType.INDICATOR_TYPE_RSI;

public class DecisionProcessorsStore {

    private final Map<String, IndicatorDecisionProcessor> processors;

    public DecisionProcessorsStore(RsiDecisionProcessor rsiDecisionProcessor) {
        processors = new HashMap<>();
        processors.put(INDICATOR_TYPE_RSI.name(), rsiDecisionProcessor);
    }

    public IndicatorDecisionProcessor get(IndicatorType type) {
        return processors.get(type);
    }

}
