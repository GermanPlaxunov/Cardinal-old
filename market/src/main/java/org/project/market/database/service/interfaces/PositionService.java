package org.project.market.database.service.interfaces;

import org.project.market.database.entity.PositionEntity;

public interface PositionService {

    PositionEntity findOpenPosition(String symbol);

    void save(PositionEntity entity);

}
