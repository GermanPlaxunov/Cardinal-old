package org.project.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.project.core.database.entity.PriceDiffSignalEntity;
import org.project.model.DiffSignal;

@Mapper(componentModel = "spring")
public interface PriceDiffSignalMapper {

    @Mapping(target = "diffSignal", source = "priceDiffSignal")
    PriceDiffSignalEntity map(DiffSignal diffSignal);

    DiffSignal map(PriceDiffSignalEntity entity);

}
