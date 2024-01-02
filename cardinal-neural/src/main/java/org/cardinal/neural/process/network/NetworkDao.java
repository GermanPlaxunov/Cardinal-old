package org.cardinal.neural.process.network;

import lombok.RequiredArgsConstructor;
import org.cardinal.data.services.interfaces.neural.NeuralNetworkService;
import org.cardinal.neural.process.training.NetworkVectorProcessor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class NetworkDao {

    private final NetworkVectorProcessor networkVectorProcessor;
    private final NeuralNetworkService neuralNetworkService;

    public List<SimpleNeuralNetwork> loadAllNetworks() {
        var networks = new ArrayList<SimpleNeuralNetwork>();
        var entities = neuralNetworkService.findAll();
        for(var network : entities) {
            networks.add(networkVectorProcessor.buildNetworkFromVector(network.getVector()));
        }
        return networks;
    }

}
