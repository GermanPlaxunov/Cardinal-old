package org.cardinal.cardinalbroker.rest;

import org.project.model.broker.bond.BondInfo;
import org.project.model.broker.bond.BondRequest;
import org.project.model.broker.currency.CurrencyInfo;
import org.project.model.broker.currency.CurrencyRequest;

import java.util.List;

public interface BrokerController {

    List<String> findAllAvailableCurrencies();

    CurrencyInfo findCurrencyInfo(CurrencyRequest currencyRequest);

    List<String> findAllAvailableBonds();

    BondInfo findBondInfo(BondRequest bondRequest);

}
