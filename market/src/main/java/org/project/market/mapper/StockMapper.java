package org.project.market.mapper;

import org.mapstruct.Mapper;
import org.libra.data.entities.MarketStockEntity;
import org.project.model.MarketStock;

@Mapper(componentModel = "spring")
public interface StockMapper {

    MarketStock map(MarketStockEntity entity);

}
