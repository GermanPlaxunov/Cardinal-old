package org.libra.data.services.interfaces.neural;

import org.libra.data.entities.neural.NeuralNetworkEntity;

import java.util.List;

public interface NeuralNetworkService {

    void save(String symbol, String name, String vector);

    List<NeuralNetworkEntity> findAllBySymbol(String symbol);

    List<NeuralNetworkEntity> findAll();

}
