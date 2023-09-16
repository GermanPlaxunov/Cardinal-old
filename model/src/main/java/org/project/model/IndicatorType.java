package org.project.model;

public enum IndicatorType {
    INDICATOR_TYPE_APO("APO"),
    INDICATOR_TYPE_BBAND("BBAND"),
    INDICATOR_TYPE_EMA("EMA"),
    INDICATOR_TYPE_RSI("RSI"),
    INDICATOR_TYPE_SMA("SMA"),
    INDICATOR_TYPE_STD("STD");

    private final String name;

    IndicatorType(String name) {
        this.name = name;
    }

}
