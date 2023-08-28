package org.project.neural.process.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.neural.process.NeuralProcessStarter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NeuralAppController implements NeuralController {

    private final NeuralProcessStarter neuralProcessStarter;

    @Override
    @PostMapping(path = "/predict",
            consumes = MediaType.APPLICATION_JSON_VALUE +
                    WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8")
    public Double predict(@RequestParam(name = "symbol") String symbol) {
        return neuralProcessStarter.predict(symbol);
    }

    @Override
    @PostMapping(path = "/train",
            consumes = MediaType.APPLICATION_JSON_VALUE +
                    WebUtils.CONTENT_TYPE_CHARSET_PREFIX + "UTF-8")
    public void train(@RequestParam(name = "symbol") String symbol) {
        neuralProcessStarter.train(symbol);
    }
}
