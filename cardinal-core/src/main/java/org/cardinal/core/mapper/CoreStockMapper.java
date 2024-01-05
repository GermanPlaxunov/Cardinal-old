package org.cardinal.core.mapper;

import org.mapstruct.Mapper;
import org.cardinal.data.entities.CoreStockEntity;
import org.cardinal.model.CoreStock;
import org.cardinal.model.MarketStock;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoreStockMapper {

    CoreStockEntity mapToCoreStockEntity(MarketStock stock);

    CoreStock mapToCore(CoreStockEntity stock);

    List<CoreStock> mapAllToCore(List<CoreStockEntity> entities);
}
