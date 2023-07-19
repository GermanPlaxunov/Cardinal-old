package org.project.core.core.process.strategy;

import lombok.RequiredArgsConstructor;
import org.project.core.database.service.interfaces.PriceDiffSignalService;
import org.project.model.MarketStock;

@RequiredArgsConstructor
public class PriceDiffStrategyProcess {

    private final PriceDiffSignalService priceDiffSignalService;

    public void startProcess(MarketStock marketStock) {
        var count = priceDiffSignalService.count(marketStock.getSymbol());
        if(count == 0){

        } else {

        }
        //1)Check if there is previous stocks
        //2)If initial create zero signal
        //3)else compare
    }

}
