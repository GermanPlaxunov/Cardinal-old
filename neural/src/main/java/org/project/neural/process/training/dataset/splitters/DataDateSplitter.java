package org.project.neural.process.training.dataset.splitters;

import org.libra.bragi.entities.indicators.DateDataItem;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataDateSplitter {

    /**
     * Filters out all data items lying between edge
     * values of interval starting from the latest date.
     * Items should be sorted in asc mode.
     *
     * @param items      - the list of data items.
     * @param intervalSeconds - interval between each sequent data items.
     * @return filtered list of data items.
     */
    public <T extends DateDataItem> List<T> split(List<T> items, Long intervalSeconds) {
        if (ifSplittingCanBeApplied(items, intervalSeconds)) {
            var filteredList = new ArrayList<T>();
            filteredList.add(items.get(items.size() - 1));
            var stepDate = items.get(items.size() - 1)
                    .getDate()
                    .minusSeconds(intervalSeconds);
            for (var i = items.size() - 2; i > 0; i--) {
                if (checkDate(items.get(i), items.get(i - 1), stepDate)) {
                    filteredList.add(items.get(i));
                    stepDate = stepDate.minusSeconds(intervalSeconds);
                }
            }
            Collections.reverse(filteredList);
            return filteredList;
        } else {
            //If splitting can not be applied returns original list
            return items;
        }
    }

    /**
     * Checks if current data item is fit as edge of interval.
     *
     * @param current  - current item.
     * @param previous - previous item.
     * @param stepDate - edge date of the step.
     * @return true if current should be added to the list.
     */
    private <T extends DateDataItem> boolean checkDate(T current, T previous, LocalDateTime stepDate) {
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
     * Checks if the distance between two data items not
     * larger than the interval to split.
     *
     * @param items   - original list of data items.
     * @param stepInterval - split interval.
     * @return true if splitting can be applied.
     */
    private <T extends DateDataItem> boolean ifSplittingCanBeApplied(List<T> items,
                                                                     long stepInterval) {
        var date_1 = items.get(items.size() - 1).getDate();
        var date_2 = items.get(items.size() - 2).getDate();
        var duration = ChronoUnit.SECONDS.between(date_2, date_1);
        return duration < stepInterval;
    }
}
