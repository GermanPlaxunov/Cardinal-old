package org.cardinal.cardinalbroker.dataprovider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.tinkoff.piapi.contract.v1.Etf;
import ru.tinkoff.piapi.core.InvestApi;

import java.util.List;

/**
 * Получение инвестиционного фонда
 */
@Slf4j
@RequiredArgsConstructor
public class EtfsDataprovider implements Dataprovider<Etf> {

    private final InvestApi investApi;

    /**
     * Возвращает инвестиционный фонд по имени
     *
     * @param name - имя фонда
     * @return фонд
     */
    @Override
    public Etf getByName(String name) {
        return investApi.getInstrumentsService()
                .getAllEtfsSync()
                .stream()
                .filter(etf -> etf.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    /**
     * Список названий инвестиционных фондов
     *
     * @return список имен
     */
    @Override
    public List<String> getAllNames() {
        return investApi.getInstrumentsService()
                .getAllEtfsSync()
                .stream()
                .map(Etf::getName)
                .toList();
    }
}
