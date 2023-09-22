package org.project.neural.process.network;

import lombok.extern.slf4j.Slf4j;
import org.project.data.services.interfaces.neural.NeuralNetworkService;
import org.project.neural.process.training.NetworkVectorProcessor;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class NetworkStore {
    private final NetworkVectorProcessor networkVectorProcessor;
    private final NeuralNetworkService neuralNetworkService;
    private final Map<String, SimpleNeuralNetwork> networks;
    private final NetworkDao networkDao;

    public NetworkStore(NetworkVectorProcessor networkVectorProcessor,
                        NeuralNetworkService neuralNetworkService,
                        NetworkDao networkDao) {
        this.networkVectorProcessor = networkVectorProcessor;
        this.neuralNetworkService = neuralNetworkService;
        this.networkDao = networkDao;
        networks = new HashMap<>();
        loadNetworkFromDb();
    }

    private void loadNetworkFromDb() {
        for(var network : networkDao.loadAllNetworks()) {
            log.info("Load Neural Network from database: {}", network.getName());
            networks.put(network.getName(), network);
        }
    }

    public void updateNetwork(String type, String symbol, SimpleNeuralNetwork network) {
        var name = getNetworkName(type, symbol);
        var vector = networkVectorProcessor.getVector(network);
        neuralNetworkService.save(symbol, name, vector);
        networks.put(name, network);
    }

    public SimpleNeuralNetwork get(String type, String symbol) {
        var name = getNetworkName(type, symbol);
        return getOrCreateNew(name);
    }

    private SimpleNeuralNetwork getOrCreateNew(String name) {
        SimpleNeuralNetwork network;
        if(networks.containsKey(name)) {
            network = networks.get(name);
        } else {
            network = new SimpleNeuralNetwork(name);
            networks.put(name, network);
        }
        return network;
    }

    private String getNetworkName(String type, String symbol) {
        return type.concat("->")
                .concat(symbol);
    }

}
