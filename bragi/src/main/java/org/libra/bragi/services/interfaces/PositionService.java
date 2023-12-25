package org.libra.bragi.services.interfaces;

import org.libra.bragi.entities.PositionEntity;

public interface PositionService {

    PositionEntity findOpenPosition(String symbol);

    PositionEntity findLatestClosedPosition(String symbol);

    void save(PositionEntity entity);

    boolean ifOpenPosition(String symbol);

}
