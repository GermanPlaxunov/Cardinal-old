package org.project.core.mapper;

import org.mapstruct.Mapper;
import org.libra.data.entities.CoreStockEntity;
import org.project.model.CoreStock;
import org.project.model.MarketStock;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockMapper {

    CoreStockEntity mapToCoreStockEntity(MarketStock stock);

    CoreStock mapToCore(CoreStockEntity stock);

    List<CoreStock> mapAllToCore(List<CoreStockEntity> entities);
}
