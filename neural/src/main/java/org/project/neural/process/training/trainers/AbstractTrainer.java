package org.project.neural.process.training.trainers;

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

    /**
     * In dataset each datapoint contains answer
     * for the previous one.
     *
     * @param dataset - entire dataset.
     * @return the answers.
     */
    protected List<Double> extractAnswers(List<List<Double>> dataset) {
        var answers = new ArrayList<Double>();
        for (var i = 1; i < dataset.size(); i++) {
            answers.add(dataset.get(i).get(1));
        }
        return answers;
    }

}
