package org.cardinal.cardinalbroker.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.tinkoff.piapi.core.InvestApi;

import java.util.concurrent.ExecutionException;

@Slf4j
@RequiredArgsConstructor
public class SandBoxAccess {

    private final String accessToken;

    public InvestApi getSandBoxConnection() {
        var api = InvestApi.createSandbox(accessToken);
        if (ifNoExistingAccount(api)) {
            var accountId = api.getSandboxService().openAccountSync();
            log.info("Created new sandbox account: {}", accountId);
        }
        return api;
    }

    private boolean ifNoExistingAccount(InvestApi api) {
        try {
            var accounts = api.getSandboxService()
                    .getAccounts()
                    .get();
            for (var account : accounts) {
                log.info("sandbox account id: {}, access level: {}", account.getId(), account.getAccessLevel().name());
            }
            return accounts.size() == 0;
        } catch (ExecutionException | InterruptedException e) {
            log.error("Error while getting sandbox accounts list", e);
        }
        return false;
    }

}
