package org.project.neural.process.training.dataset.splitters;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.project.data.entities.indicators.AbsolutePriceOscillatorEntity;
import org.project.data.entities.indicators.IndicatorDate;
import org.project.neural.TestDataProvider;

import static org.junit.jupiter.api.Assertions.*;

class IndicatorSplitterTest {

    private static IndicatorSplitter<AbsolutePriceOscillatorEntity> splitter;

    @BeforeAll
    public static void setUp() {
        splitter = new IndicatorSplitter<>();
    }

    @Test
    void split() {
        var apos = TestDataProvider.getApoList(100);
        var filteredApos = splitter.split(apos, 300L);
        assertEquals(20, filteredApos.size());
        for (var i = filteredApos.size() - 1; i > 1 ; i--) {
            var delta = filteredApos.get(i).getValue() - filteredApos.get(i - 1).getValue();
            assertEquals(5.0, delta);
        }
    }
}