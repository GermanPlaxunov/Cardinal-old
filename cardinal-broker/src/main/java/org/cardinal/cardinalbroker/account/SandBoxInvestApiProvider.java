package org.cardinal.cardinalbroker.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.tinkoff.piapi.core.InvestApi;

@Slf4j
@RequiredArgsConstructor
public class SandBoxInvestApiProvider {

    private final String accessToken;

    /**
     * Creates new connection to sandbox API.
     *
     * @return investAPI
     */
    public InvestApi getSandBoxConnection() {
        var api = InvestApi.createSandbox(accessToken);
        if (ifNoExistingAccount(api)) {
            var accountId = api.getSandboxService().openAccountSync();
            log.info("Created new sandbox account: {}", accountId);
        }
        return api;
    }

    private boolean ifNoExistingAccount(InvestApi api) {
        var accounts = api.getSandboxService()
                .getAccountsSync();
        for (var account : accounts) {
            log.info("sandbox account id: {}, access level: {}", account.getId(), account.getAccessLevel().name());
        }
        return accounts.size() == 0;
    }

}
