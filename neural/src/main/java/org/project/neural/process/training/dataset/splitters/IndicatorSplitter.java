package org.project.neural.process.training.dataset.splitters;

import org.project.data.entities.indicators.IndicatorDate;

import java.util.ArrayList;
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
        var filteredList = new ArrayList<T>();
        filteredList.add(indicators.get(indicators.size() - 1));
        var lastDate = indicators.get(indicators.size() - 1)
                .getDate()
                .minusSeconds(intervalSeconds);
        for (var i = indicators.size() - 1; i > 0; i--) {
            if (indicators.get(i).getDate().equals(lastDate)) {
                filteredList.add(indicators.get(i));
                lastDate = lastDate.minusSeconds(intervalSeconds);
            }
        }
        return filteredList;
    }

}
