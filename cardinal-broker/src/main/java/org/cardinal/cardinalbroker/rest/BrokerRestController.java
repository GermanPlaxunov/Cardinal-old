package org.cardinal.cardinalbroker.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cardinal.cardinalbroker.dataprovider.CurrencyDataprovider;
import org.cardinal.cardinalbroker.mapper.CurrencyMapper;
import org.project.model.broker.currency.CurrencyInfo;
import org.project.model.broker.currency.CurrencyRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BrokerRestController implements BrokerController {

    private static final String CONSUMES = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8";
    private static final String PRODUCES = MediaType.APPLICATION_JSON_VALUE + WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8";

    private final CurrencyDataprovider currencyDataprovider;
    private final CurrencyMapper currencyMapper;

    @GetMapping(path = "/find-all-available-currencies")
    public List<String> findAllAvailableCurrencies() {
        return currencyDataprovider.getAllCurrenciesNames();
    }

    @PostMapping(path = "/find-currency-info",
            consumes = CONSUMES, produces = PRODUCES)
    public CurrencyInfo findCurrencyInfo(@RequestBody CurrencyRequest currencyRequest) {
        var currencyName = currencyRequest.getCurrencyName();
        var currency = currencyDataprovider.getCurrency(currencyName);
        log.info("Currency: {}", currency);
        return currencyMapper.map(currency);
    }

}
