package org.project.market.database.service.classes;

import lombok.RequiredArgsConstructor;
import org.project.market.database.entity.LastProvidedStockEntity;
import org.project.market.database.repository.LastProvidedStockRepository;
import org.project.market.database.service.interfaces.LastProvidedStockService;

@RequiredArgsConstructor
public class LastProvidedStockServiceImpl implements LastProvidedStockService {

    private final LastProvidedStockRepository lastProvidedStockRepository;

    @Override
    public LastProvidedStockEntity find(String symbol) {
        return lastProvidedStockRepository.findBySymbol(symbol)
                .orElse(null);
    }
}
