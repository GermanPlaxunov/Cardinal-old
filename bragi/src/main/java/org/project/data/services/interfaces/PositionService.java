package org.project.data.services.interfaces;

import org.project.data.entities.PositionEntity;

public interface PositionService {

    PositionEntity findOpenPosition(String symbol);

    PositionEntity findLatestClosedPosition(String symbol);

    void save(PositionEntity entity);

    boolean ifOpenPosition(String symbol);

}
