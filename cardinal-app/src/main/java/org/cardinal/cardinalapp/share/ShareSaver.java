package org.cardinal.cardinalapp.share;

import lombok.RequiredArgsConstructor;
import org.cardinal.cardinalutils.mapper.ShareMapper;
import org.cardinal.data.services.interfaces.ShareService;
import ru.tinkoff.piapi.contract.v1.Share;

import java.util.List;

@RequiredArgsConstructor
public class ShareSaver {

    private final ShareService shareService;
    private final ShareMapper shareMapper;

    public void saveShares(List<Share> shares) {
        for (var share : shares) {
            shareService.save(shareMapper.mapToEntity(share));
        }
    }

}
