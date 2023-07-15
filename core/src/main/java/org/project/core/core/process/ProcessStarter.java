package org.project.core.core.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.core.client.MlClient;
import org.project.core.core.market.MarketDataProvider;
import org.project.core.database.service.interfaces.CoreStockService;
import org.project.model.MarketStock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RequiredArgsConstructor
public class ProcessStarter {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.SSSSSS");
    private final MarketDataProvider marketDataProvider;
    private final CoreStockService coreStockService;
    private final MlClient mlClient;

    public void startProcess(String stock) {
        var next = marketDataProvider.getNextDataPoint(stock);
        var count = coreStockService.countBySymbol(stock);
        if (count >= 20) {
            var fromDate = localDateTimeToString(getFromDate(next));
            var toDate = localDateTimeToString(next.getDate());
            log.info("Send request to ML: from {} to {} symbol {}", fromDate, toDate, stock);
            var decision = mlClient.analyze(fromDate, toDate, stock);
            log.info("ML decision: {}", decision);
        }
    }

    private LocalDateTime getFromDate(MarketStock marketStock) {
        return marketStock.getDate().minusMinutes(20);
    }

    private String localDateTimeToString(LocalDateTime date) {
        return date.format(formatter);
    }

}
