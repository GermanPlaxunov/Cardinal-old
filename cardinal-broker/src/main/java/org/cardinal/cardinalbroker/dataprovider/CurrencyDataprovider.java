package org.cardinal.cardinalbroker.dataprovider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.tinkoff.piapi.contract.v1.Currency;
import ru.tinkoff.piapi.core.InvestApi;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CurrencyDataprovider {

    private final InvestApi investApi;

    /**
     * Return currency by it`s name.
     *
     * @return currency
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
     * Returns the list of currencies names.
     *
     * @return list of names
     */
    public List<String> getAllCurrenciesNames() {
        return investApi.getInstrumentsService()
                .getAllCurrenciesSync()
                .stream()
                .map(Currency::getName)
                .toList();
    }

}
