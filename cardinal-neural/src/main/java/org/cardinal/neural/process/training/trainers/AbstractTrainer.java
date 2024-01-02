package org.cardinal.neural.process.training.trainers;

import java.util.ArrayList;
import java.util.List;

public class AbstractTrainer {

    /**
     * The last element was obtained for current time.
     * It should be removed from the dataset because we don`t
     * know the answer yet.
     *
     * @param dataset - entire dataset.
     */
    protected void removeLastElement(List<List<Double>> dataset) {
        var size = dataset.size();
        dataset.remove(size - 1);
    }

}
