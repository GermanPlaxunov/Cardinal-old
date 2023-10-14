package org.project.neural;

import org.project.data.entities.CoreStockEntity;
import org.project.data.entities.indicators.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TestDataProvider {

    public static LocalDate START_DATE = LocalDate.of(2017, 1, 1);
    public static LocalTime START_TIME = LocalTime.of(0, 0, 0);

    public static List<CoreStockEntity> getTestStocks(int size) {
        var stocks = new ArrayList<CoreStockEntity>();
        var date = LocalDateTime.of(START_DATE, START_TIME);
        var close = 1.0;
        for (var i = 0; i < size; i++) {
            stocks.add(new CoreStockEntity()
                    .setClose(close)
                    .setDate(date));
            date = date.plusSeconds(60);
            close++;
        }
        return stocks;
    }

    public static List<AbsolutePriceOscillatorEntity> getApoList(int size) {
        var apos = new ArrayList<AbsolutePriceOscillatorEntity>();
        var date = LocalDateTime.of(START_DATE, START_TIME);
        var value = 1.0;
        for (var i = 0; i < size; i++) {
            apos.add(new AbsolutePriceOscillatorEntity()
                    .setDate(date)
                    .setValue(value));
            date = date.plusSeconds(60);
            value++;
        }
        return apos;
    }

    public static List<BollingerBandsEntity> getBbandList(int size) {
        var bbands = new ArrayList<BollingerBandsEntity>();
        var date = LocalDateTime.of(START_DATE, START_TIME);
        var upper = 5.0;
        var middle = 3.0;
        var lower = 1.0;
        for (var i = 0; i < size; i++) {
            bbands.add(new BollingerBandsEntity()
                    .setDate(date)
                    .setUpper(upper)
                    .setMiddle(middle)
                    .setLower(lower));
            date = date.plusSeconds(60);
            upper += 2;
            middle += 2;
            lower += 2;
        }
        return bbands;
    }

    public static List<ExponentialMovingAverageEntity> getEmaList(int size) {
        var emas = new ArrayList<ExponentialMovingAverageEntity>();
        var date = LocalDateTime.of(START_DATE, START_TIME);
        var value = 0.0;
        for (var i = 0; i < size; i++) {
            emas.add(new ExponentialMovingAverageEntity()
                    .setDate(date)
                    .setValue(value));
            date = date.plusSeconds(60);
            value += 5;
        }
        return emas;
    }

    public static List<RelativeStrengthIndicatorEntity> getRsiList(int size) {
        var rsis = new ArrayList<RelativeStrengthIndicatorEntity>();
        var date = LocalDateTime.of(START_DATE, START_TIME);
        var gains = 100.0;
        var losses = 100.0;
        var rsi = gains / losses;
        for (var i = 0; i < size; i++) {
            rsis.add(new RelativeStrengthIndicatorEntity()
                    .setRsi(rsi)
                    .setGainSumm(gains)
                    .setLossSumm(losses)
                    .setDate(date));
            date = date.plusSeconds(60);
            gains += 2.0;
            losses += 1.5;
            rsi = gains / losses;
        }
        return rsis;
    }

    public static List<SimpleMovingAverageEntity> getSmaList(int size) {
        var emas = new ArrayList<SimpleMovingAverageEntity>();
        var date = LocalDateTime.of(START_DATE, START_TIME);
        var value = 0.0;
        for (var i = 0; i < size; i++) {
            emas.add(new SimpleMovingAverageEntity()
                    .setDate(date)
                    .setValue(value));
            date = date.plusSeconds(60);
            value += 5;
        }
        return emas;
    }

    public static List<StandardDerivativesEntity> getStdList(int size) {
        var emas = new ArrayList<StandardDerivativesEntity>();
        var date = LocalDateTime.of(START_DATE, START_TIME);
        var value = 0.0;
        for (var i = 0; i < size; i++) {
            emas.add(new StandardDerivativesEntity()
                    .setDate(date)
                    .setValue(value));
            date = date.plusSeconds(60);
            value += 5;
        }
        return emas;
    }
}
