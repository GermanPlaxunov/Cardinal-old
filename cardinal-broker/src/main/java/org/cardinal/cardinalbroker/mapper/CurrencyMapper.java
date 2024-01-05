package org.cardinal.cardinalbroker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.cardinal.model.broker.currency.CurrencyInfo;
import ru.tinkoff.piapi.contract.v1.Currency;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

    @Mapping(source = "name", target = "name")
    CurrencyInfo map(Currency currency);

}

