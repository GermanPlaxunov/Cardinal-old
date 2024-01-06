package org.cardinal.cardinalapp.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cardinal.cardinalapp.process.dataprovider.NextCandleDataprovider;
import org.cardinal.cardinalutils.mapper.CandleMapper;
import org.cardinal.data.services.interfaces.ProcessParamsService;
import org.cardinal.data.services.interfaces.ShareService;
import org.cardinal.data.services.interfaces.history.CandleService;
import org.cardinal.model.job.ProcessStarter;

@Slf4j
@RequiredArgsConstructor
public class MainProcessStarter implements ProcessStarter {

    private final NextCandleDataprovider nextCandleDataprovider;
    private final ProcessParamsService processParamsService;
    private final CandleService candleService;
    private final ShareService shareService;
    private final CandleMapper candleMapper;

    @Override
    public void startProcess() {
        log.info("Started main process");
        var instrumentId = getFigiOfActiveInstrument();
        updatePriceInfo(instrumentId);
    }

    private void updatePriceInfo(String instrumentId) {
        var newCandles = nextCandleDataprovider.getNextCandles(instrumentId);
        newCandles.stream()
                .map(candle -> candleMapper.mapToCandle(candle))
                .map(candle -> candleMapper.mapToEntity(candle))
                .forEach(candle -> candleService.save(candle));
    }

    private String getFigiOfActiveInstrument() {
        var name = processParamsService.getActiveTradeInstrumentName();
        return shareService.findByName(name)
                .getFigi();
    }

}
