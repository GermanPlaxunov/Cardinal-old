package org.project.core.mapper;

import org.mapstruct.Mapper;
import org.project.data.entities.CoreStockEntity;
import org.project.model.MarketStock;

@Mapper(componentModel = "spring")
public interface StockMapper {

    CoreStockEntity mapToCore(MarketStock stock);

}
