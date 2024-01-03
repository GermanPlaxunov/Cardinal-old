package org.cardinal.cardinalbroker.config;

import org.cardinal.cardinalbroker.account.SandBoxInvestApiProvider;
import org.cardinal.cardinalbroker.dataprovider.BondsDataprovider;
import org.cardinal.cardinalbroker.dataprovider.CurrencyDataprovider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.piapi.core.InvestApi;

@Configuration
public class BrokerBeansConfig {

    @Bean
    public InvestApi investApi(@Value("${tinkoff.invest.token}") String accessToken) {
        var apiProvider = new SandBoxInvestApiProvider(accessToken);
        return apiProvider.getSandBoxConnection();
    }

    @Bean
    public CurrencyDataprovider currencyDataprovider(InvestApi investApi) {
        return new CurrencyDataprovider(investApi);
    }

    @Bean
    public BondsDataprovider bondsDataprovider(InvestApi investApi) {
        return new BondsDataprovider(investApi);
    }

}
