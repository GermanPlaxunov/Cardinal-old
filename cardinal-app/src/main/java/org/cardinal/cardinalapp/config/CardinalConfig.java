package org.cardinal.cardinalapp.config;

import org.cardinal.cardinalapp.process.MainProcessStarter;
import org.cardinal.cardinalapp.process.dataprovider.NextCandleDataprovider;
import org.cardinal.cardinalbroker.config.BrokerBeansConfig;
import org.cardinal.cardinalbroker.dataprovider.candles.CandlesDataprovider;
import org.cardinal.cardinalutils.mapper.CandleMapper;
import org.cardinal.core.config.CoreBeansConfig;
import org.cardinal.data.services.interfaces.LastProvidedStockService;
import org.cardinal.data.services.interfaces.ProcessParamsService;
import org.cardinal.data.services.interfaces.ShareService;
import org.cardinal.data.services.interfaces.history.CandleService;
import org.cardinal.gate.config.GateBeansConfig;
import org.cardinal.model.job.ProcessStarter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CoreBeansConfig.class, GateBeansConfig.class,
        BrokerBeansConfig.class})
public class CardinalConfig {

    @Bean
    public ProcessStarter processStarter(NextCandleDataprovider nextCandleDataprovider,
                                         ProcessParamsService processParamsService,
                                         CandleService candleService,
                                         ShareService shareService,
                                         CandleMapper candleMapper) {
        return new MainProcessStarter(nextCandleDataprovider,
                processParamsService,
                candleService,
                shareService,
                candleMapper);
    }

    @Bean
    public NextCandleDataprovider nextCandleDataprovider(LastProvidedStockService lastProvidedStockService,
                                                         ProcessParamsService processParamsService,
                                                         CandlesDataprovider candlesDataprovider) {
        return new NextCandleDataprovider(lastProvidedStockService,
                processParamsService,
                candlesDataprovider);
    }

}
