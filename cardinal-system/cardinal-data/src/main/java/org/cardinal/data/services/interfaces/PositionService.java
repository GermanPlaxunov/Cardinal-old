package org.cardinal.data.services.interfaces;

import org.cardinal.data.entities.PositionEntity;

public interface PositionService {

    PositionEntity findOpenPosition(String figi);

    PositionEntity findLatestClosedPosition(String figi);

    void save(PositionEntity entity);

    boolean ifOpenPosition(String figi);

}
