package org.cardinal.neural.process;

import java.util.List;

public class Utils {

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
