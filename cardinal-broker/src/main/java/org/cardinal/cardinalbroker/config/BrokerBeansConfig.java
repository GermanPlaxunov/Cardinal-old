package org.cardinal.cardinalbroker.config;

import org.cardinal.cardinalbroker.account.SandBoxInvestApiProvider;
import org.cardinal.cardinalbroker.api.BrokerApi;
import org.cardinal.cardinalbroker.dataprovider.candles.CandlesDataprovider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.piapi.core.InvestApi;

@Configuration
public class BrokerBeansConfig {

    @Bean
    public InvestApi investApi(@Value("${sandbox.token}") String accessToken) {
        var apiProvider = new SandBoxInvestApiProvider(accessToken);
        return apiProvider.getSandBoxConnection();
    }

    @Bean
    public CandlesDataprovider candlesDataprovider(InvestApi investApi) {
        return new CandlesDataprovider(investApi);
    }

    @Bean
    public BrokerApi brokerApi(CandlesDataprovider candlesDataprovider,
                               InvestApi investApi) {
        return new BrokerApi(candlesDataprovider,
                investApi);
    }

}
