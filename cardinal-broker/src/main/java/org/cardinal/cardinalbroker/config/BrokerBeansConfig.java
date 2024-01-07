package org.cardinal.cardinalbroker.config;

import org.cardinal.cardinalbroker.account.SandBoxInvestApiProvider;
import org.cardinal.cardinalbroker.candle.CandleProcessor;
import org.cardinal.cardinalbroker.dataprovider.candles.CandlesDataprovider;
import org.cardinal.cardinalutils.config.UtilsConfig;
import org.cardinal.cardinalutils.mapper.CandleMapper;
import org.cardinal.data.config.DataBeansConfig;
import org.cardinal.data.services.interfaces.ProcessParamsService;
import org.cardinal.data.services.interfaces.history.CandleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.tinkoff.piapi.core.InvestApi;

@Configuration
@Import({DataBeansConfig.class, UtilsConfig.class})
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
    public CandleProcessor candleProcessor(ProcessParamsService processParamsService,
                                           CandlesDataprovider candlesDataprovider,
                                           CandleService candleService,
                                           CandleMapper candleMapper) {
        return new CandleProcessor(processParamsService,
                candlesDataprovider,
                candleService,
                candleMapper);
    }

}
