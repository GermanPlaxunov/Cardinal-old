package org.project.core.mapper;

import org.mapstruct.Mapper;
import org.project.core.database.entity.PriceDiffSignalEntity;
import org.project.model.DiffSignal;

@Mapper(componentModel = "spring")
public interface PriceDiffSignalMapper {

    PriceDiffSignalEntity map(DiffSignal diffSignal);

    DiffSignal map(PriceDiffSignalEntity entity);

}
