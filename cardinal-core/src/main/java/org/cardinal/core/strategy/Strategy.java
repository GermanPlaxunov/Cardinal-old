package org.cardinal.core.strategy;

public interface Strategy {

    void openNewPositionProcess(String figi);

    void closeExistingPositionProcess(String figi);

}
