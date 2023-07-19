package org.project.core.database.service.interfaces;

import org.project.core.database.entity.PriceDiffSignalEntity;
import org.project.model.MarketStock;

import java.util.List;

public interface PriceDiffSignalService {

    List<PriceDiffSignalEntity> findAll(String symbol);

    Long count(String symbol);

    void save(PriceDiffSignalEntity entity);

    PriceDiffSignalEntity findPrevSignal(MarketStock stock);

}
