package org.project.neural.process.training.dataset.splitters;

import org.project.data.entities.indicators.IndicatorDate;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IndicatorSplitter<T extends IndicatorDate> {

    /**
     * Filters out all indicators values lying between edge
     * indicators of interval starting from the latest date.
     * Indicators should be sorted in asc mode.
     *
     * @param indicators      - the list of indicators.
     * @param intervalSeconds - interval between each sequent indicators.
     * @return filtered list of indicators.
     */
    public List<T> split(List<T> indicators, Long intervalSeconds) {
        if (ifSplittingCanBeApplied(indicators, intervalSeconds)) {
            var filteredList = new ArrayList<T>();
            filteredList.add(indicators.get(indicators.size() - 1));
            var stepDate = indicators.get(indicators.size() - 1)
                    .getDate()
                    .minusSeconds(intervalSeconds);
            for (var i = indicators.size() - 2; i > 0; i--) {
                if (checkDate(indicators.get(i), indicators.get(i - 1), stepDate)) {
                    filteredList.add(indicators.get(i));
                    stepDate = stepDate.minusSeconds(intervalSeconds);
                }
            }
            Collections.reverse(filteredList);
            return filteredList;
        } else {
            //If splitting can not be applied returns original list
            return indicators;
        }
    }

    /**
     * Checks if current indicator is fit as edge of interval.
     *
     * @param current  - current indicator.
     * @param previous - previous indicator.
     * @param stepDate - edge date of the step.
     * @return true if current should be added to the list.
     */
    private boolean checkDate(T current, T previous, LocalDateTime stepDate) {
        var d1 = current.getDate();
        var d2 = previous.getDate();
        if (d1.equals(stepDate)) {
            return true;
        } else {
            if (d2.equals(stepDate)) {
                return false;
            } else {
                if (d1.isBefore(stepDate) && d2.isAfter(stepDate)) {
                    return true;
                }
                if (d1.isBefore(stepDate) && d2.isBefore(stepDate)) {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the distance between two indicators not
     * larger than the interval to split.
     *
     * @param indicators   - original list of indicators.
     * @param stepInterval - split interval.
     * @return true if splitting can be applied.
     */
    private boolean ifSplittingCanBeApplied(List<T> indicators,
                                            long stepInterval) {
        var date_1 = indicators.get(indicators.size() - 1).getDate();
        var date_2 = indicators.get(indicators.size() - 2).getDate();
        var duration = ChronoUnit.SECONDS.between(date_2, date_1);
        return duration < stepInterval;
    }

}
