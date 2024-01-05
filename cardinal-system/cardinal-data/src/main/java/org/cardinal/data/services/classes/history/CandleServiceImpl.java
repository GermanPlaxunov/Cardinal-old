package org.cardinal.data.services.classes.history;

import lombok.RequiredArgsConstructor;
import org.cardinal.data.entities.history.CandleEntity;
import org.cardinal.data.repositories.history.CandleRepository;
import org.cardinal.data.services.interfaces.history.CandleService;

import java.util.List;

/**
 * Сервис для сохранения свечей
 */
@RequiredArgsConstructor
public class CandleServiceImpl implements CandleService {

    private final CandleRepository candleRepository;

    @Override
    public void save(CandleEntity candle) {
        candleRepository.save(candle);
    }

    @Override
    public List<CandleEntity> findAllByInstrumentId(String instrumentId) {
        return candleRepository.findAllByInstrumentId(instrumentId);
    }
}
