package org.libra.bragi.services.interfaces;

import org.libra.bragi.entities.PriceDiffSignalEntity;

import java.util.List;

public interface PriceDiffSignalService {

    List<PriceDiffSignalEntity> findAll(String symbol);

    Long count(String symbol);

    void save(PriceDiffSignalEntity entity);

    PriceDiffSignalEntity findPrevSignal(String symbol);

}
