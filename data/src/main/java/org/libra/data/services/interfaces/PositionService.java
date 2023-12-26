package org.libra.data.services.interfaces;

import org.libra.data.entities.PositionEntity;

public interface PositionService {

    PositionEntity findOpenPosition(String symbol);

    PositionEntity findLatestClosedPosition(String symbol);

    void save(PositionEntity entity);

    boolean ifOpenPosition(String symbol);

}
