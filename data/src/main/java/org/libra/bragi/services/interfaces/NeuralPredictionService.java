package org.libra.bragi.services.interfaces;

import org.libra.bragi.entities.NeuralPredictionEntity;
import org.project.model.Indicators;

import java.util.List;

public interface NeuralPredictionService {

    List<NeuralPredictionEntity> findPredictionsOverPeriod(String symbol, Indicators indicator, long periodSeconds);

    void save(NeuralPredictionEntity entity);

    NeuralPredictionEntity findLast(String symbol, Indicators indicator);
}
