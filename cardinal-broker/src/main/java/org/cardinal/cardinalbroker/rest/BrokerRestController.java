package org.cardinal.cardinalbroker.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cardinal.cardinalbroker.account.SandBoxAccess;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BrokerRestController implements BrokerController {

    private final SandBoxAccess sandBoxAccess;

    @GetMapping(path = "/openNewAccount")
    public void checkSandBox() {
        var api = sandBoxAccess.getSandBoxConnection();
        var accounts = api.getSandboxService().getAccountsSync();
        for(var account : accounts) {
            log.info("accountId: {}", account.getId());
        }
    }

}
