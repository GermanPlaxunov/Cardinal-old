package org.cardinal.cardinalbroker.dataprovider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.tinkoff.piapi.contract.v1.Future;
import ru.tinkoff.piapi.core.InvestApi;

import java.util.List;

/**
 * Получение фьючерсов
 */
@Slf4j
@RequiredArgsConstructor
public class FuturesDataprovider implements Dataprovider<Future> {

    private final InvestApi investApi;

    /**
     * Возвращает фьючерс по имени
     *
     * @param name - имя фьючерса
     * @return фьючерс
     */
    @Override
    public Future getByName(String name) {
        return investApi.getInstrumentsService()
                .getAllFuturesSync()
                .stream()
                .filter(future -> future.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    /**
     * Возвращает список имен фьючерсов
     *
     * @return список имен
     */
    @Override
    public List<String> getAllNames() {
        return investApi.getInstrumentsService()
                .getAllFuturesSync()
                .stream()
                .map(Future::getName)
                .toList();
    }
}
