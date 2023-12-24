package org.project.data.services.interfaces;

import org.project.data.entities.NeuralPredictionEntity;
import org.project.model.Indicators;

import java.util.List;

public interface NeuralPredictionService {

    List<NeuralPredictionEntity> findPredictionsOverPeriod(String symbol, Indicators indicator, long periodSeconds);

    void save(NeuralPredictionEntity entity);

    NeuralPredictionEntity findLast(String symbol, Indicators indicator);
}
