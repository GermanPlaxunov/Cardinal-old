package org.project.data.services.interfaces;

import org.project.data.entities.ProcessParamsEntity;
import org.project.model.Indicators;

import java.util.List;

public interface ProcessParamsService {

    void save(ProcessParamsEntity entity);

    List<ProcessParamsEntity> findAllByNames(List<String> names);

    Long getTrainInterval(String symbol, Indicators indicator);

    Long getTrainCacheDepth(String symbol, Indicators indicator);

    Long getVerifyDatasetDepth(String symbol, Indicators indicator);

    Long getStepBackSeconds(String symbol, Indicators indicator);
}
