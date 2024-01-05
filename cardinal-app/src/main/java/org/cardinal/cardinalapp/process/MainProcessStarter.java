package org.cardinal.cardinalapp.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cardinal.cardinalapp.process.dataprovider.NextCandleDataprovider;
import org.cardinal.cardinalutils.mapper.CandleMapper;
import org.cardinal.data.services.interfaces.history.CandleService;
import org.cardinal.model.job.ProcessStarter;

@Slf4j
@RequiredArgsConstructor
public class MainProcessStarter implements ProcessStarter {

    private final NextCandleDataprovider nextCandleDataprovider;
    private final CandleService candleService;
    private final CandleMapper candleMapper;

    @Override
    public void startProcess(String instrumentId) {
        log.info("Started main process");
        updatePriceInfo(instrumentId);
    }

    private void updatePriceInfo(String instrumentId) {
        var newCandles = nextCandleDataprovider.getNextCandles(instrumentId);
        newCandles.stream()
                .map(candle -> candleMapper.mapToCandle(candle))
                .map(candle -> candleMapper.mapToEntity(candle))
                .forEach(candle -> candleService.save(candle));
    }

}
