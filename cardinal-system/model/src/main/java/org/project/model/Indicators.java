package org.project.model;

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

    public static Indicators getFromName(String name) {
        return Arrays.stream(Indicators.values())
                .filter(val -> val.name.equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

}
