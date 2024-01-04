package org.cardinal.cardinalbroker.dataprovider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.tinkoff.piapi.contract.v1.Currency;
import ru.tinkoff.piapi.core.InvestApi;

import java.util.List;

/**
 * Получение данных по валютам
 */
@Slf4j
@RequiredArgsConstructor
public class CurrencyDataprovider implements Dataprovider<Currency> {

    private final InvestApi investApi;

    /**
     * Получение валюты по имени
     *
     * @return валюта
     */
    @Override
    public Currency getByName(String name) {
        return investApi.getInstrumentsService()
                .getAllCurrenciesSync()
                .stream()
                .filter(currency -> currency.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    /**
     * Возвращает список названий всех доступных валют
     *
     * @return список имен валют
     */
    @Override
    public List<String> getAllNames() {
        return investApi.getInstrumentsService()
                .getAllCurrenciesSync()
                .stream()
                .map(Currency::getName)
                .toList();
    }

}
