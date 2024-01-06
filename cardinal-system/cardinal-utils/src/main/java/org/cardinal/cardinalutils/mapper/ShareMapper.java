package org.cardinal.cardinalutils.mapper;

import org.cardinal.data.entities.ShareEntity;
import ru.tinkoff.piapi.contract.v1.Share;

public class ShareMapper {

    public ShareEntity mapToEntity(Share share) {
        return new ShareEntity()
                .setFigi(share.getFigi())
                .setTicker(share.getTicker())
                .setName(share.getName())
                .setBuyAvailableFlag(share.getBuyAvailableFlag())
                .setApiTradeAvailableFlag(share.getApiTradeAvailableFlag());
    }

}
