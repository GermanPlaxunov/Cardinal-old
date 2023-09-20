package org.project.core.core.process.indicators;

import java.util.Arrays;
import java.util.List;

public enum Indicators {
    APO("APO"),
    BBAND("BBAND"),
    EMA("EMA"),
    RSI("RSI"),
    SMA("SMA"),
    STD("STD");

    private final String name;

    Indicators(String name) {
        this.name = name;
    }

    public static List<String> getAllNames() {
        return Arrays.stream(Indicators.values())
                .map(Indicators::name)
                .toList();
    }

}
