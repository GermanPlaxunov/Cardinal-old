package org.project.neural.process.training;

import org.project.model.Indicators;
import org.project.neural.process.training.trainers.NetworkRsiTrainer;

import java.util.HashMap;
import java.util.Map;

public class TrainersStore {

    private final Map<Indicators, Trainer> trainers;

    public TrainersStore(NetworkRsiTrainer networkRsiTrainer) {
        trainers = new HashMap<>();
        trainers.put(Indicators.RSI, networkRsiTrainer);
    }

    public Trainer get(Indicators indicator) {
        return trainers.get(indicator);
    }

}
