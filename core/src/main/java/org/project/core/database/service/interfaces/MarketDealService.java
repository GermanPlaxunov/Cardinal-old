package org.project.core.database.service.interfaces;

import org.project.core.database.entity.MarketDealEntity;

public interface MarketDealService {
    void openDeal(String stockName,
                  String dealType,
                  Double openPrice,
                  Double dealAmountCurr,
                  Double dealAmountUsd);

    MarketDealEntity findOpenDeal(String stockName);

    void closeDeal(String stockName, Double closePrice);

}
