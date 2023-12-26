package org.project.core.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.core.core.process.TradeProcessStarter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CoreRestController implements CoreController {

    private final TradeProcessStarter tradeProcessStarter;

    @PostMapping(path = "/startProcess")
    public void startProcess(@RequestParam(name = "symbol") String symbol) {
        tradeProcessStarter.startProcess(symbol);
    }

}
