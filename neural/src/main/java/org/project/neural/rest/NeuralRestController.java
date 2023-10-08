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
    public Double predict(@RequestParam(name = "symbol") String symbol,
                          @RequestParam(name = "indicator") String indicator) {
        var prediction = neuralProcessStarter.predict(symbol, indicator);
        log.info("Prediction for {} {} = {}", symbol, indicator, prediction);
        return prediction;
    }

    @Override
    @PostMapping(path = "/train")
    public void train(@RequestParam(name = "symbol") String symbol) {
        neuralProcessStarter.train(symbol);
    }
}
