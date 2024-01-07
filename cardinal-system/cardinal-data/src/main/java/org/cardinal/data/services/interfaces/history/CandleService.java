package org.cardinal.data.services.interfaces.history;

import org.cardinal.data.entities.history.CandleEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface CandleService {

    void save(CandleEntity candle);

    void saveAll(List<CandleEntity> candles);

    List<CandleEntity> findAllByInstrumentId(String instrumentId);

    LocalDateTime findLastCandleDateByFigi(String figi);

    boolean existHistory(String figi);

}
