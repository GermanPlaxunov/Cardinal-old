package org.cardinal.data.services.classes.history;

import lombok.RequiredArgsConstructor;
import org.cardinal.data.entities.history.CandleEntity;
import org.cardinal.data.repositories.history.CandleRepository;
import org.cardinal.data.services.interfaces.history.CandleService;

import java.time.LocalDateTime;
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
    public void saveAll(List<CandleEntity> candles) {
        candleRepository.saveAll(candles);
    }

    @Override
    public List<CandleEntity> findAllByInstrumentId(String figi) {
        return candleRepository.findAllByFigi(figi);
    }

    @Override
    public LocalDateTime findLastCandleDateByFigi(String figi) {
        return candleRepository.findTopByFigiOrderByDateTimeDesc(figi)
                .map(CandleEntity::getDateTime)
                .orElse(null);
    }

    @Override
    public boolean existHistory(String figi) {
        return candleRepository.existsByFigi(figi);
    }
}
