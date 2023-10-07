package org.project.neural.process.training.dataset;

import org.project.data.entities.CoreStockEntity;

import java.util.List;
import java.util.Map;

public interface DatasetProvider {

    /**
     * Should return a set of data for
     * learning any type of neural network.
     *
     * @param symbol
     * @return dataset
     */
    List<Map<String, Double>> getData(String symbol, List<CoreStockEntity> stocks);

}
