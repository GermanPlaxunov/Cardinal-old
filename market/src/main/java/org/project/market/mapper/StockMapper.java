package org.project.market.mapper;

import org.mapstruct.Mapper;
import org.project.market.database.entity.StockEntity;
import org.project.model.MarketStock;

@Mapper(componentModel = "spring")
public interface StockMapper {

    MarketStock map(StockEntity entity);

}
