package org.project.core.core.process.strategy;

public class MainStrategy {

    /**
     * Check if new position should be opened.
     *
     * @param symbol - stock name.
     * @return final decision.
     */
    public boolean ifNewPositionShouldBeOpened(String symbol) {
        return true;
    }

    /**
     * Check if current position should be closed.
     *
     * @param symbol - stock name.
     * @return final decision.
     */
    public boolean ifCurrentPositionShouldBeClosed(String symbol) {
        return true;
    }

}
