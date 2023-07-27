package org.project.data.services.interfaces;

import org.project.data.entities.PriceDiffSignalEntity;
import org.project.model.MarketStock;

import java.util.List;

public interface PriceDiffSignalService {

    List<PriceDiffSignalEntity> findAll(String symbol);

    Long count(String symbol);

    void save(PriceDiffSignalEntity entity);

    PriceDiffSignalEntity findPrevSignal(MarketStock stock);

}
