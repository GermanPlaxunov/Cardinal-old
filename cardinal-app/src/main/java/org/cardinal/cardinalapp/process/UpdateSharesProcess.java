package org.cardinal.cardinalapp.process;

import lombok.RequiredArgsConstructor;
import org.cardinal.cardinalapp.share.ShareSaver;
import org.cardinal.cardinalbroker.api.BrokerApi;

@RequiredArgsConstructor
public class UpdateSharesProcess {

    private final ShareSaver shareSaver;
    private final BrokerApi brokerApi;

    public void updateShares() {
        var shares = brokerApi.getAllShares();

    }

}
