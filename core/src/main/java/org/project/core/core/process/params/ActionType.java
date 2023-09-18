package org.project.core.core.process.params;

public enum ActionType {
    ACTION_TYPE_TRADE("TRADE"),
    ACTION_TYPE_RSI_DECISION("RSI_DECISION");

    private final String name;

    ActionType(String name) {
        this.name = name;
    }

}
