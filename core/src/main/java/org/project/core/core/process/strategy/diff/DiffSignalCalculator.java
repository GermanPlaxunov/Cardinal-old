package org.project.core.core.process.strategy.diff;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.core.database.service.interfaces.CoreStockService;
import org.project.core.database.service.interfaces.PriceDiffSignalService;
import org.project.core.mapper.PriceDiffSignalMapper;
import org.project.model.DiffSignal;
import org.project.model.MarketStock;

@Slf4j
@RequiredArgsConstructor
public class DiffSignalCalculator {

    private final PriceDiffSignalService priceDiffSignalService;
    private final PriceDiffSignalMapper priceDiffSignalMapper;
    private final CoreStockService coreStockService;

    public DiffSignal createInitialSignal(MarketStock stock) {
        var signal = new DiffSignal()
                .setSymbol(stock.getSymbol())
                .setPrevPrice(0.0)
                .setCurrPrice(stock.getClose())
                .setDiff(0.0)
                .setPriceDiffSignal(0)
                .setPositionSignal(0);
        saveSignal(signal);
        return signal;
    }

    public DiffSignal createDiffSignal(MarketStock stock) {
        var prevSignal = priceDiffSignalService.findPrevSignal(stock);
        var prevStock = coreStockService.findPrevStock(stock);
        var diff = stock.getClose() - prevStock.getClose();
        var priceDiffSignal = getPriceDiffSignal(diff);
        var posSig = getPositionSignal(prevSignal.getDiffSignal(), priceDiffSignal);
        var signal = new DiffSignal()
                .setSymbol(prevStock.getSymbol())
                .setPrevPrice(prevStock.getClose())
                .setCurrPrice(stock.getClose())
                .setDiff(diff)
                .setPriceDiffSignal(priceDiffSignal)
                .setPositionSignal(posSig);
        saveSignal(signal);
        return signal;
    }

    private void saveSignal(DiffSignal signal) {
        priceDiffSignalService.save(priceDiffSignalMapper.map(signal));
    }

    private int getPriceDiffSignal(Double diff) {
        return diff > 0 ? 1 : 0;
    }

    private int getPositionSignal(Integer prevDiffSig, Integer currDiffSig) {
        Integer posSig = 0;
        if (prevDiffSig == 0 && currDiffSig == 0) {
            posSig = 0;
        }
        if (prevDiffSig == 0 && currDiffSig == 1) {
            posSig = 1;
        }
        if (prevDiffSig == 1 && currDiffSig == 0) {
            posSig = -1;
        }
        if (prevDiffSig == 1 && currDiffSig == 1) {
            posSig = 0;
        }
        return posSig;
    }

}
