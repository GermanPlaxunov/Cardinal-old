package org.project.neural.process.network;

import java.util.HashMap;
import java.util.Map;

public class NetworkProvider {

    private final Map<String, SimpleNeuralNetwork> networks;

    public NetworkProvider() {
        networks = new HashMap<>();
        networks.put("RSI->BTC/USD", new SimpleNeuralNetwork("RSI->BTC/USD"));
    }

    public SimpleNeuralNetwork get(String name) {
        return networks.get(name);
    }

}
