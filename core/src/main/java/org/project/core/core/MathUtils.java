package org.project.core.core;

import java.util.List;

public class MathUtils {

    public static <T extends Number> Double standardDeviation(List<T> inputArray) {
        var sum = 0.0;
        for (var num : inputArray) {
            sum += (double) num;
        }

        var mean = sum / inputArray.size();

        var standardDeviation = 0.0;
        for (var num : inputArray) {
            standardDeviation += Math.pow((double) num - mean, 2);
        }
        return Math.sqrt(standardDeviation / inputArray.size());
    }

    public static Double getSumm(List<Double> elements) {
        return elements.stream()
                .reduce(0.0, Double::sum);
    }

    public static double sigmoid(double in) {
        return 1 / (1 + Math.exp(-in));
    }

    public static Double meanSquareLoss(List<Double> correctAnswers, List<Double> predictedAnswers) {
        var sumSquare = 0.0;
        for (var i = 0; i < correctAnswers.size(); i++) {
            var error = correctAnswers.get(i) - predictedAnswers.get(i);
            sumSquare += (error * error);
        }
        return sumSquare / correctAnswers.size();
    }

}
