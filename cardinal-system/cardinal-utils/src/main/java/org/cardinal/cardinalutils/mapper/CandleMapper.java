package org.cardinal.cardinalutils.mapper;

import com.google.protobuf.Timestamp;
import org.cardinal.data.entities.history.CandleEntity;
import org.cardinal.model.broker.Candle;
import ru.tinkoff.piapi.contract.v1.HistoricCandle;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static ru.tinkoff.piapi.core.utils.MapperUtils.quotationToBigDecimal;


public class CandleMapper {

    /**
     * Маппит свечу модели в сущность для БД.
     *
     * @param candle - свеча модели
     * @return сущность
     */
    public CandleEntity mapToEntity(Candle candle) {
        return new CandleEntity()
                .setFigi(candle.getFigi())
                .setOpen(candle.getOpen())
                .setLow(candle.getLow())
                .setHigh(candle.getHigh())
                .setClose(candle.getClose())
                .setDateTime(candle.getDateTime());

    }

    /**
     * Маппит свечу, полученную из Tinkoff API
     * в свечу модели Cardinal.
     *
     * @param figi           - financial instrument global identifier
     * @param historicCandle - свеча из Tinkoff
     * @return свеча модели
     */
    public Candle mapToCandle(String figi, HistoricCandle historicCandle) {
        return new Candle()
                .setFigi(figi)
                .setOpen(quotationToBigDecimal(historicCandle.getOpen()))
                .setLow(quotationToBigDecimal(historicCandle.getLow()))
                .setHigh(quotationToBigDecimal(historicCandle.getHigh()))
                .setClose(quotationToBigDecimal(historicCandle.getClose()))
                .setVolume(historicCandle.getVolume())
                .setDateTime(toLocalDateTime(historicCandle.getTime()));
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

}
