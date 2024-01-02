package org.cardinal.neural.process.training;

import org.cardinal.neural.process.training.training.TrainParams;

public interface NetworkTrainer {

    /**
     * Should train the network using appropriate dataset.
     * As answers networks should predict the price change
     * in the future.
     *
     * @param params - contains information for training.
     */
    void train(TrainParams params);

}
