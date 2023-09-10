package org.project.neural.process.network;

import lombok.RequiredArgsConstructor;
import org.project.data.services.interfaces.neural.NeuralNetworkService;
import org.project.neural.process.training.NetworkVectorProcessor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class NetworkStore {
    private final NetworkVectorProcessor networkVectorProcessor;
    private final NeuralNetworkService neuralNetworkService;
    private final Map<String, SimpleNeuralNetwork> networks;

    public NetworkStore(NetworkVectorProcessor networkVectorProcessor,
                        NeuralNetworkService neuralNetworkService) {
        this.networkVectorProcessor = networkVectorProcessor;
        this.neuralNetworkService = neuralNetworkService;
        networks = new HashMap<>();
        networks.put("RSI->BTC/USD", new SimpleNeuralNetwork("RSI->BTC/USD"));
    }

    public void updateNetwork(String type, String symbol, SimpleNeuralNetwork network) {
        var name = getNetworkName(type, symbol);
        var vector = networkVectorProcessor.getVector(network);
        neuralNetworkService.save(symbol, name, vector);
        networks.put(name, network);
    }

    public SimpleNeuralNetwork get(String type, String symbol) {
        var name = getNetworkName(type, symbol);
        return networks.get(name);
    }

    private String getNetworkName(String type, String symbol) {
        return type.concat("->").concat(symbol);
    }

}
