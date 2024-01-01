package org.libra.data.services.interfaces;

import org.libra.data.entities.ProcessParamsEntity;
import org.project.model.Indicators;

import java.util.List;

public interface ProcessParamsService {

    void save(ProcessParamsEntity entity);

    List<ProcessParamsEntity> findAllByNames(List<String> names);

    Long getProcessCacheDepth(String symbol);

    Long getTrainInterval(String symbol, Indicators indicator);

    Long getTrainCacheDepth(String symbol, Indicators indicator);

    Long getVerifyDatasetDepth(String symbol, Indicators indicator);

    Long getStepBackSeconds(String symbol, Indicators indicator);

    Long getShortTrendCacheDepth(String symbol);

    Long getNumberOfPeriodsToFindTrend(String symbol);

    Long getMaximumCacheDepth(String symbol);

    Double getBuyCommissionPercentage(String symbol);

    Double getSellCommissionPercentage(String symbol);

    Double getMaxBalanceShareForTrade();

}
