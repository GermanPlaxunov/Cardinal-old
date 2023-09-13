package org.project.neural.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.neural.process.NeuralProcessStarter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NeuralRestController implements NeuralController {

    private final NeuralProcessStarter neuralProcessStarter;

    @Override
    @PostMapping(path = "/predict")
    public Double predict(@RequestParam(name = "symbol") String symbol) {
        return neuralProcessStarter.predict(symbol);
    }

    @Override
    @PostMapping(path = "/train")
    public void train(@RequestParam(name = "symbol") String symbol) {
        neuralProcessStarter.train(symbol);
    }
}
