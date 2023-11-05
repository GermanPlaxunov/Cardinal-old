package org.project.core.core.process.indicators;

import lombok.RequiredArgsConstructor;
import org.project.core.core.process.indicators.model.Bband;
import org.project.core.core.process.vars.ProcessVars;
import org.project.data.entities.indicators.*;
import org.project.data.services.interfaces.indicators.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class IndicatorsSaver {

    private final AbsolutePriceOscillatorService absolutePriceOscillatorService;
    private final BollingerBandsService bollingerBandsService;
    private final ExponentialMovingAverageService exponentialMovingAverageService;
    private final RelativeStrengthIndicatorService relativeStrengthIndicatorService;
    private final SimpleMovingAverageService simpleMovingAverageService;
    private final StandardDerivativesService standardDerivativesService;

    public void saveAllIndicators(ProcessVars vars) {
        var symbol = vars.getSymbol();
        var depth = vars.getDepth();
        var date = vars.getDate();
        var gSum = vars.getRsi().getGainSumm();
        var lSum = vars.getRsi().getLossSumm();
        saveApo(symbol, depth, vars.getApo(), date);
        saveBband(symbol, depth, vars.getBband(), date);
        saveEma(symbol, depth, vars.getEma(), date);
        saveRsi(symbol, depth, vars.getRsi().getRsi(), date, gSum, lSum);
        saveSma(symbol, depth, vars.getSma(), date);
        saveStd(symbol, depth, vars.getStd(), date);
    }

    private void saveApo(String symbol, Long depth, Double apo, LocalDateTime date) {
        absolutePriceOscillatorService.save(new AbsolutePriceOscillatorEntity()
                .setSymbol(symbol)
                .setDepth(depth)
                .setValue(apo)
                .setDate(date));
    }

    private void saveBband(String symbol, Long depth, Bband bband, LocalDateTime date) {
        bollingerBandsService.save(new BollingerBandsEntity()
                .setSymbol(symbol)
                .setDepth(depth)
                .setUpper(bband.getUpper())
                .setMiddle(bband.getMiddle())
                .setLower(bband.getLower())
                .setDate(date));
    }

    private void saveEma(String symbol, Long depth, Double ema, LocalDateTime date) {
        exponentialMovingAverageService.save(new ExponentialMovingAverageEntity()
                .setSymbol(symbol)
                .setDepth(depth)
                .setValue(ema)
                .setDate(date));
    }

    private void saveRsi(String symbol, Long depth, Double rsi, LocalDateTime date, Double gainSum, Double lossSum) {
        relativeStrengthIndicatorService.save(new RelativeStrengthEntityDataItem()
                .setSymbol(symbol)
                .setDepth(depth)
                .setRsi(rsi)
                .setDate(date)
                .setGainSumm(gainSum)
                .setLossSumm(lossSum));
    }

    private void saveSma(String symbol, Long depth, Double sma, LocalDateTime date) {
        simpleMovingAverageService.save(new SimpleMovingAverageEntity()
                .setSymbol(symbol)
                .setDepth(depth)
                .setValue(sma)
                .setDate(date));
    }

    private void saveStd(String symbol, Long depth, Double std, LocalDateTime date) {
        standardDerivativesService.save(new StandardDerivativesEntity()
                .setSymbol(symbol)
                .setDepth(depth)
                .setValue(std)
                .setDate(date));
    }

}
