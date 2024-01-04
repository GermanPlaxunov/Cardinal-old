package org.cardinal.cardinalbroker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.project.model.broker.bond.BondInfo;
import ru.tinkoff.piapi.contract.v1.Bond;

@Mapper(componentModel = "spring")
public interface BondMapper {

    @Mapping(source = "name", target = "name")
    BondInfo map(Bond bond);

}
