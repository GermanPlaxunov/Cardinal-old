package org.cardinal.data.services.classes;

import lombok.RequiredArgsConstructor;
import org.cardinal.data.entities.ShareEntity;
import org.cardinal.data.repositories.ShareRepository;
import org.cardinal.data.services.interfaces.ShareService;

@RequiredArgsConstructor
public class ShareServiceImpl implements ShareService {

    private final ShareRepository shareRepository;

    @Override
    public void save(ShareEntity shareEntity) {
        shareRepository.save(shareEntity);
    }

    @Override
    public ShareEntity findByName(String name) {
        return shareRepository.findByName(name)
                .orElse(null);
    }
}
