package org.cardinal.data.services.interfaces.history;

import org.cardinal.data.entities.history.CandleEntity;

import java.util.List;

public interface CandleService {

    void save(CandleEntity candle);

    List<CandleEntity> findAllByInstrumentId(String instrumentId);
}
