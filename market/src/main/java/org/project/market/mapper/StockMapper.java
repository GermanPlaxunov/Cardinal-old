package org.project.market.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.project.market.database.entity.StockEntity;
import org.project.model.MarketStock;

import java.util.Locale;

@Mapper(componentModel = "spring")
public interface StockMapper {

    MarketStock map(StockEntity entity);

}
