package org.cardinal.cardinalbroker.dataprovider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.tinkoff.piapi.contract.v1.Share;
import ru.tinkoff.piapi.core.InvestApi;

import java.util.List;

/**
 * Получение списка акций
 */
@Slf4j
@RequiredArgsConstructor
public class SharesDataprovider implements Dataprovider<Share> {

    private final InvestApi investApi;


    /**
     * Получение акции по имени
     *
     * @param name - имя акции
     * @return акция
     */
    @Override
    public Share getByName(String name) {
        return investApi.getInstrumentsService()
                .getAllSharesSync()
                .stream()
                .filter(share -> share.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    /**
     * Получение спика имен акций
     *
     * @return список имен
     */
    @Override
    public List<String> getAllNames() {
        return investApi.getInstrumentsService()
                .getAllSharesSync()
                .stream()
                .map(Share::getName)
                .toList();
    }
}
