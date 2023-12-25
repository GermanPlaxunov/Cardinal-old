package org.project.neural.process.network;

import lombok.extern.slf4j.Slf4j;
import org.libra.bragi.services.interfaces.neural.NeuralNetworkService;
import org.project.model.Indicators;
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

    public void updateNetwork(Indicators indicator, String symbol, SimpleNeuralNetwork network) {
        var name = getNetworkName(indicator.name(), symbol);
        var vector = networkVectorProcessor.getVector(network);
        neuralNetworkService.save(symbol, name, vector);
        networks.put(name, network);
    }

    public SimpleNeuralNetwork get(Indicators indicator, String symbol) {
        return getOrCreateNew(symbol, indicator);
    }

    private SimpleNeuralNetwork getOrCreateNew(String symbol, Indicators indicator) {
        var name = getNetworkName(indicator.name(), symbol);
        SimpleNeuralNetwork network;
        if(networks.containsKey(name)) {
            network = networks.get(name);
        } else {
            network = new SimpleNeuralNetwork(name);
            networks.put(name, network);
            updateNetwork(indicator, symbol, network);
        }
        return network;
    }

    private String getNetworkName(String type, String symbol) {
        return type.concat("->")
                .concat(symbol);
    }

}
