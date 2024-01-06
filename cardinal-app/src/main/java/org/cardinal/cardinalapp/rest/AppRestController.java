package org.cardinal.cardinalapp.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cardinal.cardinalapp.share.ShareSaver;
import org.cardinal.cardinalbroker.api.BrokerApi;
import org.cardinal.gate.ParamsSaver;
import org.cardinal.model.ParamData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AppRestController implements AppController {

    private final ParamsSaver paramsSaver;
    private final ShareSaver shareSaver;
    private final BrokerApi brokerApi;

    @Override
    @PostMapping(path = "/save-new-process-param")
    public void saveNewParam(ParamData paramData) {
        paramsSaver.saveNewParam(paramData);
    }

    @Override
    @GetMapping(path = "/get-api-trade-available-instrument")
    public List<String> getInstrumentIds() {
        return brokerApi.getApiTradeAvailableSharesFigiIds();
    }

    @GetMapping(path = "/saveAllShares")
    public void saveShares() {
        var shares = brokerApi.getAllShares();
        shareSaver.saveShares(shares);
    }

}
