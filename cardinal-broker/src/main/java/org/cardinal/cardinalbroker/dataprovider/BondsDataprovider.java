package org.cardinal.cardinalbroker.dataprovider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.tinkoff.piapi.contract.v1.Bond;
import ru.tinkoff.piapi.core.InvestApi;

import java.util.List;

/**
 * Получение данных по облигациям
 */
@Slf4j
@RequiredArgsConstructor
public class BondsDataprovider {

    private final InvestApi investApi;

    public List<String> getAllBondsNames() {
        return investApi.getInstrumentsService()
                .getAllBondsSync()
                .stream()
                .map(bond -> {
                    log.info("{}", bond);
                    return bond.getName();
                })
                .toList();
    }

    public Bond getBondByName(String bondName) {
        return investApi.getInstrumentsService()
                .getAllBondsSync()
                .stream()
                .filter(bond -> bond.getName().equalsIgnoreCase(bondName))
                .findFirst()
                .orElse(null);
    }

}
