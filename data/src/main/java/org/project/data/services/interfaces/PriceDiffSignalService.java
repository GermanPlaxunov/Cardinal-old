package org.project.data.services.interfaces;

import org.project.data.entities.PriceDiffSignalEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceDiffSignalService {

    List<PriceDiffSignalEntity> findAll(String symbol);

    Long count(String symbol);

    void save(PriceDiffSignalEntity entity);

    PriceDiffSignalEntity findPrevSignal(String symbol);

}
