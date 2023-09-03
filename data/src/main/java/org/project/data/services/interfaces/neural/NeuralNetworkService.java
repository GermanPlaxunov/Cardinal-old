package org.project.data.services.interfaces.neural;

import org.project.data.entities.neural.NeuralNetworkEntity;

import java.util.List;

public interface NeuralNetworkService {

    void save(NeuralNetworkEntity entity);

    List<NeuralNetworkEntity> findAllBySymbol(String symbol);

}
