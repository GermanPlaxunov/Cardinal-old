package org.project.data.services.classes;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.NeuralPredictionEntity;
import org.project.data.repositories.NeuralPredictionRepository;
import org.project.data.services.interfaces.NeuralPredictionService;
import org.project.model.Indicators;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class NeuralPredictionServiceImpl implements NeuralPredictionService {

    private final NeuralPredictionRepository repository;

    @Override
    public List<NeuralPredictionEntity> findPredictionsOverPeriod(String symbol, Indicators indicator, long periodSeconds) {
        var last = repository.findFirstBySymbolAndIndicatorNameOrderByDateDesc(symbol, indicator.name())
                .orElse(null);
        if (last != null && last.getDate() != null) {
            var lastDate = last.getDate()
                    .minusSeconds(periodSeconds);
            return repository.findAllBySymbolAndIndicatorNameAndDateGreaterThanOrderByDateAsc(symbol, indicator.name(), lastDate);
        }
        return Collections.emptyList();
    }

    @Override
    public void save(NeuralPredictionEntity entity) {
        repository.saveAndFlush(entity);
    }

    @Override
    public NeuralPredictionEntity findLast(String symbol, Indicators indicator) {
        return repository.findFirstBySymbolAndIndicatorNameOrderByDateDesc(symbol, indicator.name())
                .orElse(null);
    }
}
