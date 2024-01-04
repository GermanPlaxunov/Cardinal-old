package org.cardinal.cardinalbroker.dataprovider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.tinkoff.piapi.contract.v1.Option;
import ru.tinkoff.piapi.core.InvestApi;

import java.util.List;

/**
 * Получение опционов
 */
@Slf4j
@RequiredArgsConstructor
public class OptionsDataprovider implements Dataprovider<Option> {

    private final InvestApi investApi;

    /**
     * Возвращает опцион по имени
     *
     * @param name - имя опциона
     * @return опцион
     */
    @Override
    public Option getByName(String name) {
        return investApi.getInstrumentsService()
                .getAllOptionsSync()
                .stream()
                .filter(option -> option.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    /**
     * Возвращает список имен опционов
     *
     * @return список имен
     */
    @Override
    public List<String> getAllNames() {
        return investApi.getInstrumentsService()
                .getAllOptionsSync()
                .stream()
                .map(Option::getName)
                .toList();
    }
}
