package org.cardinal.cardinalapp.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cardinal.gate.ParamsSaver;
import org.cardinal.model.ParamData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AppRestController implements AppController {

    private final ParamsSaver paramsSaver;

    @Override
    @PostMapping(path = "/save-new-process-param")
    public void saveNewParam(@RequestBody ParamData paramData) {
        paramsSaver.saveNewParam(paramData);
    }

}
