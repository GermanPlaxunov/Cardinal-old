package org.project.neural.process.training.dataset;

import org.cardinal.data.entities.CoreStockEntity;

import java.util.ArrayList;
import java.util.List;

public interface DatasetProvider {

    /**
     * Should return a set of data for
     * learning any type of neural network.
     * Answer is the first item of each list.
     *
     * @param symbol - the stock name.
     * @return dataset
     */
    List<List<Double>> getData(String symbol, List<CoreStockEntity> stocks);

    /**
     * In dataset each datapoint contains answer
     * for the previous one.
     *
     * @param dataset - entire dataset.
     * @return the answers.
     */
    default List<Double> extractAnswers(List<List<Double>> dataset) {
        var answers = new ArrayList<Double>();
        for (var i = 1; i < dataset.size(); i++) {
            answers.add(dataset.get(i).get(0));
        }
        return answers;
    }

    /**
     * According to the rule that first item of each row is answer,
     * Each list should contain [size - 1]`th and [size - 2]`th
     * elements of dataset.
     *
     * @param dataset - dataset
     * @return list of questions.
     */
    default List<List<Double>> getQuestions(List<List<Double>> dataset) {
        var questions = new ArrayList<List<Double>>();
        Integer rowSize;
        for (var i = 0; i < dataset.size(); i++) {
            rowSize = dataset.get(i).size();
            var question = new ArrayList<Double>();
            question.add(dataset.get(i).get(rowSize - 1));
            question.add(dataset.get(i).get(rowSize - 2));
            questions.add(question);
        }
        return questions;
    }

}
