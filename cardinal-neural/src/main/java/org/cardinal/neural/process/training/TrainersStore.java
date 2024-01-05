package org.cardinal.neural.process.training;

import org.cardinal.neural.process.training.trainers.*;
import org.cardinal.model.Indicators;

import java.util.HashMap;
import java.util.Map;

public class TrainersStore {

    private final Map<Indicators, NetworkTrainer> trainers;

    public TrainersStore(NetworkApoTrainer networkApoTrainer,
                         NetworkBbandTrainer networkBbandTrainer,
                         NetworkEmaTrainer networkEmaTrainer,
                         NetworkRsiTrainer networkRsiTrainer,
                         NetworkSmaTrainer networkSmaTrainer,
                         NetworkStdTrainer networkStdTrainer) {
        trainers = new HashMap<>();
        trainers.put(Indicators.APO, networkApoTrainer);
        trainers.put(Indicators.BBAND, networkBbandTrainer);
        trainers.put(Indicators.EMA, networkEmaTrainer);
        trainers.put(Indicators.RSI, networkRsiTrainer);
        trainers.put(Indicators.SMA, networkSmaTrainer);
        trainers.put(Indicators.STD, networkStdTrainer);
    }

    public NetworkTrainer get(Indicators indicator) {
        return trainers.get(indicator);
    }

}
