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
public class BondsDataprovider implements Dataprovider<Bond> {

    private final InvestApi investApi;

    /**
     * Получение списка названий всех облигаций
     * @return список имен
     */
    @Override
    public List<String> getAllNames() {
        return investApi.getInstrumentsService()
                .getAllBondsSync()
                .stream()
                .map(Bond::getName)
                .toList();
    }

    /**
     * Получение облигации по имени
     * @param name - имя облигации
     * @return облигации
     */
    @Override
    public Bond getByName(String name) {
        return investApi.getInstrumentsService()
                .getAllBondsSync()
                .stream()
                .filter(bond -> bond.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

}
