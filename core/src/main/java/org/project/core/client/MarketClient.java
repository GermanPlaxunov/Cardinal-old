package org.project.core.client;

import org.project.model.MarketStock;

public interface MarketClient {

    MarketStock getNextStock(String stockName);

    void openLongPosition(String stockName, Double amountCurr);

    void closeLongPosition(String stockName);

}