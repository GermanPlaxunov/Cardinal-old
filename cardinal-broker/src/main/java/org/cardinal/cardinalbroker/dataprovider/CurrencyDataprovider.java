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
public class CurrencyDataprovider {

    private final InvestApi investApi;

    /**
     * Находит валюту по имени
     *
     * @return валюта
     */
    public Currency getCurrency(String currencyName) {
        return investApi.getInstrumentsService()
                .getAllCurrenciesSync()
                .stream()
                .filter(currency -> currency.getName().equalsIgnoreCase(currencyName))
                .findFirst()
                .orElse(null);
    }

    /**
     * Возвращает список названий всех доступных валют
     *
     * @return список имен валют
     */
    public List<String> getAllCurrenciesNames() {
        return investApi.getInstrumentsService()
                .getAllCurrenciesSync()
                .stream()
                .map(currency -> {
                    log.info("{}", currency);
                    return currency.getName();
                })
                .toList();
    }

}
