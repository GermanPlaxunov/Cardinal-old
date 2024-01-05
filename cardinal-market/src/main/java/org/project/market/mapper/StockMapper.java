package org.project.market.mapper;

import org.mapstruct.Mapper;
import org.cardinal.data.entities.MarketStockEntity;
import org.cardinal.model.MarketStock;

@Mapper(componentModel = "spring")
public interface StockMapper {

    MarketStock map(MarketStockEntity entity);

}
