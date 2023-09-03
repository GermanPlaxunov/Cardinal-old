package org.project.neural.process.network;

import java.util.HashMap;
import java.util.Map;

public class NetworkStore {

    private final Map<String, SimpleNeuralNetwork> networks;

    public NetworkStore() {
        networks = new HashMap<>();
        networks.put("RSI->BTC/USD", new SimpleNeuralNetwork("RSI->BTC/USD"));
    }

    public SimpleNeuralNetwork get(String name) {
        return networks.get(name);
    }

}
