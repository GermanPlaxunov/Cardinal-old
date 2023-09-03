package org.project.gate.job;

import lombok.RequiredArgsConstructor;
import org.project.gate.client.CoreClient;
import org.project.gate.client.NeuralClient;

@RequiredArgsConstructor
public class ScheduledJobExecutor {

    private final NeuralClient neuralClient;
    private final CoreClient coreClient;

    public void trade() {
        coreClient.startProcess("BTC/USD");
    }

    public void train() {
        neuralClient.train("BTC/USD");
    }

}
