package org.project.core.database.service.classes;

import lombok.RequiredArgsConstructor;
import org.project.core.database.entity.MarketDealEntity;
import org.project.core.database.repository.MarketDealRepository;
import org.project.core.database.service.interfaces.MarketDealService;

import java.time.LocalDateTime;
import java.util.Objects;

@RequiredArgsConstructor
public class MarketDealServiceImpl implements MarketDealService {

    private final MarketDealRepository marketDealRepository;

    @Override
    public void openDeal(String stockName,
                         String dealType,
                         Double openPrice,
                         Double dealAmountCurr,
                         Double dealAmountUsd) {
        var deal = new MarketDealEntity()
                .setSymbols(stockName)
                .setDealType(dealType)
                .setOpenDate(LocalDateTime.now())
                .setOpenPrice(openPrice)
                .setDealAmountCurr(dealAmountCurr)
                .setDealAmountUsd(dealAmountUsd);
        marketDealRepository.saveAndFlush(deal);
    }

    @Override
    public MarketDealEntity findOpenDeal(String stockName) {
        return marketDealRepository.findAllBySymbolsAndCloseDateIsNull(stockName)
                .stream()
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void closeDeal(String stockName, Double closePrice) {
        var deal = findOpenDeal(stockName);
        deal.setCloseDate(LocalDateTime.now())
                .setClosePrice(closePrice);
        marketDealRepository.saveAndFlush(deal);
    }
}
